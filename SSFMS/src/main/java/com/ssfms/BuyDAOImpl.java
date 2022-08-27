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
					+ " VALUES(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getShop_No());
			pstmt.setString(2, buydto.getShop_Num());
			pstmt.setString(3, buydto.getShop_Name());
			pstmt.setString(4, buydto.getShop_Boss());
			pstmt.setString(5, buydto.getShop_Tel());
			pstmt.setString(6, buydto.getShop_Post());
			pstmt.setString(7, buydto.getShop_addr());
			pstmt.setString(8, buydto.getShop_Reg());
			
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

	
	
	

	
	//구매
	@Override
	public int insertBuy(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int result = 0;
		
		try {
			
			conn.setAutoCommit(false);
			sql = "INSERT INTO buy(buy_No, state_No, partNo, buy_Date, buy_qty, buy_price, shop_No)"
					+ " SELECT buy_No_seq.NEXTVAL, stateNum, partNo, ?, ?, ?, ? FROM accounting WHERE stateNum = ? ";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getBuy_Date());
			pstmt.setInt(2, buydto.getBuy_qty());
			pstmt.setInt(3, buydto.getBuy_price());
			pstmt.setString(4, buydto.getShop_No());
			pstmt.setString(5, buydto.getState_No()); // state_No 가져와야함? 그냥 입력하자...ㅎ
			
			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			sql = "UPDATE part SET part_stock = ? "
					+ " WHERE partNo = ? ";
			
			pstmt.setInt(1, buydto.getPart_stock() + buydto.getBuy_qty());
			pstmt.setString(2, buydto.getPartNo());
			
			result = pstmt.executeUpdate();
			
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
			
			throw e;
		}catch (SQLException e) {
			conn.rollback();
		}catch (Exception e) {
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


	@Override
	public List<BuyDTO> listBuy() {
		// TODO Auto-generated method stub
		return null;
	}


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


	

	
	
	
	
	
}







