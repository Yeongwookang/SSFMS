package com.ssfms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


import com.util.DBConn;

public class BuyDAOImpl implements BuyDAO {
	private Connection conn = DBConn.getConnection();

	
	
	//구매처 
	@Override
	public int insertShop(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		
		try {
			
			sql = "INSERT INTO shop(shop_No, shop_Num, shop_Name, shop_Boss, shop_Tel, shop_Post, shop_addr, shop_Reg)"
					+ " VALUES('SH'||TO_CHAR(shop_no_seq.NEXTVAL),?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setString(1, buydto.getShop_No());
			pstmt.setString(1, buydto.getShop_Num());
			pstmt.setString(2, buydto.getShop_Name());
			pstmt.setString(3, buydto.getShop_Boss());
			pstmt.setString(4, buydto.getShop_Tel());
			pstmt.setString(5, buydto.getShop_Post());
			pstmt.setString(6, buydto.getShop_addr());
			pstmt.setString(7, buydto.getShop_Reg());
			
			result = pstmt.executeUpdate();


		} catch (SQLIntegrityConstraintViolationException e) {
			
			if(e.getErrorCode()==1) {
				System.out.println("중복 데이터 등록은 불가합니다.");
			}else if (e.getErrorCode()==1400) {
					System.out.println("필수 사항을 입력하지 않았습니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		}catch (SQLDataException e) {
				if(e.getErrorCode()==1840 || e.getErrorCode()==1861) {
					System.out.println("날짜 입력 형식 오류입니다.");
					System.out.println("예시) 2001-01-01");
				}else {
					System.out.println(e.toString());
				}
				throw e;
				
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}

	
	@Override
	public int updateShop(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		
		try {

			sql = "UPDATE shop SET "
					+ " shop_Num = ?, "
					+ " shop_Name = ?, "
					+ " shop_Boss = ?, "
					+ " shop_Tel = ?, "
					+ " shop_Post = ?, "
					+ " shop_addr = ?, "
					+ " shop_Reg = ? "
					+ " WHERE shop_No = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getShop_Num());
			pstmt.setString(2, buydto.getShop_Name());
			pstmt.setString(3, buydto.getShop_Boss());
			pstmt.setString(4, buydto.getShop_Tel());
			pstmt.setString(5, buydto.getShop_Post());
			pstmt.setString(6, buydto.getShop_addr());
			pstmt.setString(7, buydto.getShop_Reg());
			pstmt.setString(8, buydto.getShop_No());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		}catch (SQLDataException e) {
			if(e.getErrorCode() == 1840 || e.getErrorCode()==1861) {
				System.out.println("날짜 입력 형식 오류입니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return result;
	}


	
	@Override
	public int deleteShop(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		try {
			
			sql = "DELETE FROM shop WHERE shop_No = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydto.getShop_No());
			result = pstmt.executeUpdate();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		
		return result;
	}

	
	
	// 매입처 리스트 조회
	@Override
	public List<BuyDTO> listShop() {
		List<BuyDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT shop_No, shop_Num, shop_Name, shop_Boss, "
					+ " shop_Tel, shop_Post, shop_addr, shop_Reg  FROM shop";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BuyDTO buydto = new BuyDTO();
				
				buydto.setShop_No(rs.getString("shop_No"));
				buydto.setShop_Num(rs.getString("shop_Num"));
				buydto.setShop_Name(rs.getString("shop_Name"));
				buydto.setShop_Boss(rs.getString("shop_Boss"));
				buydto.setShop_Tel(rs.getString("shop_Tel"));
				buydto.setShop_Post(rs.getString("shop_Post"));
				buydto.setShop_addr(rs.getString("shop_addr"));
				buydto.setShop_Reg(rs.getString("shop_Reg"));

				
				list.add(buydto);
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}

	
	
	

	// -----------------------------------------------------------------------------------
	//구매 이루어지는 것!!!!!!!!!!!!!!!!
	@Override
	public int insertBuy(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql;
		int result = 0;
		
		int sno=0, price=0;
		

		try {
			
			conn.setAutoCommit(false);
				
			sql = "SELECT part_price FROM part "
					+ " WHERE partNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydto.getPartNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt("part_price");
			}else {
				return 0;
			}
			
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			
			// --------------------------------------------		
			sql = "INSERT INTO buy (buy_No, stateNo, partNo, buy_Date, buy_qty, buy_price, shop_No) "
					+ " VALUES ('B_'||TO_CHAR(buy_No_seq.NEXTVAL), ?, ?, ?, ?, ?, ?) ";
			 
			
			pstmt2 = conn.prepareStatement(sql);

			pstmt2.setInt(1, buydto.getStateNo());
			pstmt2.setString(2, buydto.getPartNo());
			pstmt2.setString(3, buydto.getBuy_Date());
			pstmt2.setInt(4, buydto.getBuy_qty());
			pstmt2.setInt(5, price * buydto.getBuy_qty());
			pstmt2.setString(6, buydto.getShop_No());
			
			pstmt2.executeUpdate();

			pstmt2.close();
			pstmt2 = null;
			
			// --------------------------------------------			
			sql = "UPDATE part SET part_stock = part_stock + ? "
					+ " WHERE partNo = ? ";
			
			pstmt2 = conn.prepareStatement(sql);		
			
			pstmt2.setInt(1, buydto.getBuy_qty());
			pstmt2.setString(2, buydto.getPartNo());
			
			pstmt2.executeUpdate();
			
			result = 1;
			
			conn.commit();
			
			
			
		} catch (SQLIntegrityConstraintViolationException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			
			if(e.getErrorCode()==1400) {
				System.out.println("필수 사항을 입력하지 않았습니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		}catch (SQLDataException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			System.out.println(e.toString());
			throw e;
			
		}catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			if(pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (Exception e2) {
				}
			}
			conn.setAutoCommit(true);

		}	
		
		return result;
		
	}

	
	

	@Override
	public int updateBuy(BuyDTO buydto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int deleteBuy(BuyDTO buydto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
	// 발주리스트 전체조회
	@Override
	public List<BuyDTO> listBuy() {
		List<BuyDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT buy_No, stateNo, buy.partNo, , part.part_name, buy_Date, buy_qty, buy_price, shop_No "
					+ " FROM buy "
					+ " JOIN part ON buy.partNo = part.partNo ";
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				BuyDTO buydto = new BuyDTO();
				
				buydto.setBuy_No(rs.getString("buy_No"));
				buydto.setStateNo(rs.getInt("stateNo"));
				buydto.setPartNo(rs.getString("partNo"));
				buydto.setPart_name(rs.getString("part_name"));
				buydto.setBuy_Date(rs.getString("buy_Date"));
				buydto.setBuy_qty(rs.getInt("buy_qty"));
				buydto.setBuy_price(rs.getInt("buy_price"));
				buydto.setShop_No(rs.getString("shop_No"));
				
				list.add(buydto);
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}

	



	// 재고 조회 (원자재) 
	@Override
	public List<BuyDTO> partlistAll(String partNo) {
		List<BuyDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT partNo, part_name, part_price, part_stock "
					+ " FROM part "
					+ " WHERE partNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, partNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BuyDTO buydto = new BuyDTO();
				
				buydto.setPartNo(rs.getString("partNo"));
				buydto.setPart_name(rs.getString("part_name"));
				buydto.setPart_price(rs.getInt("part_price"));
				buydto.setPart_stock(rs.getInt("part_stock"));
				
				list.add(buydto);
				
			}
			
			
		} catch (Exception e) {

		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		
		return list;
	}


	
	
	
	
	//---------------------------------
	// 구매 전표 등록
	@Override
	public int insertAccBuy(AccDTO accdto, EmpDTO empdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		try {
			
			
			conn.setAutoCommit(false);
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, ?, ?, '153', ?, ?, '', '미승인', ?) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(2, accdto.getAccountNo());
			pstmt.setInt(3, accdto.getAmount());
			pstmt.setString(4, accdto.getDetail());
			pstmt.setString(5, accdto.getStateDate());
			
			
			result = pstmt.executeUpdate();

			conn.commit();
			
	
			
		} catch (SQLIntegrityConstraintViolationException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			
			if(e.getErrorCode() == 1) {
				System.out.println("중복 데이터로 등록이 불가능합니다.");	
			}else if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		} catch (SQLDataException e) {
			
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			
			if(e.getErrorCode() == 1840 || e.getErrorCode()==1861) {
				System.out.println("날짜 입력 형식 오류입니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		} catch (SQLException e) {
			conn.rollback();
			throw e;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {
			}
		}
		
		
		return result;
	}


	

	//등록전표조회 
	@Override
	public List<AccDTO> listAccBuy(String accountSubNo) {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT stateNo, empNo, accountNo, accounting.accountSubNo, ACCOUNTSUB.name, amount, detail, cancellation, stateCon, stateDate  "
					+ " FROM accounting "
					+ " JOIN ACCOUNTSUB ON ACCOUNTSUB.accountSubNo = accounting.accountSubNo "
					+ " WHERE accountSubNo = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountSubNo);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AccDTO accdto = new AccDTO();
				
				
				
				accdto.setStateNo(rs.getInt("StateNo"));
				accdto.setEmpNo(rs.getString("empNo"));
				accdto.setAccountNo(rs.getString("accountNo"));
				accdto.setAccountSubNo(rs.getString("accounting.accountSubNo"));
				accdto.setName(rs.getString("ACCOUNTSUB.name"));
				accdto.setAmount(rs.getInt("amount"));
				accdto.setDetail(rs.getString("detail"));
				accdto.setCancellation(rs.getString("cancellation"));
				accdto.setStateCon(rs.getString("stateCon"));
				accdto.setStateDate(rs.getDate("stateDate").toString());
				
				list.add(accdto);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
			
		}
		
		
		
		return list;
	}


	//전표상태 취소 
	@Override
	public int updateAccBuy(AccDTO accdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		
		try {
			
			sql = "UPDATE accounting SET "
					+ " cancellation = 'O' "
					+ " WHERE StateNo = ? AND stateCon = '미승인' AND accountSubNo = '153' ";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, accdto.getStateNo());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return result;
	}

	
	
}







