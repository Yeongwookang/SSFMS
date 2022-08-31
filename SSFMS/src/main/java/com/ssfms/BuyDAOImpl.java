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

	
	
	//매입처 등록하기 !!! 
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

	
	//매입처 정보 수정
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


	
	//매입처 삭제
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
		
		
		String pname, b_no = null;
		int price=0;
		String sname, saddr, sboss;
		

		try {
			
			conn.setAutoCommit(false);
				
			sql = "SELECT part_price, part_name FROM part "
					+ " WHERE partNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydto.getPartNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt("part_price");
				pname = rs.getString("part_name");
			}else {
				return 0;
			}
			
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			
			
			// --------------------------------------------	 구매 테이블에 발주 등록
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

			
			// --------------------------------------------	 해당 매입처에서 상호명, 주소 가져오기		
			
			sql = "SELECT shop_addr, shop_Name, shop_boss FROM shop "
					+ " WHERE shop_No = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydto.getShop_No());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sname = rs.getString("shop_Name");
				saddr = rs.getString("shop_addr");
				sboss = rs.getString("shop_boss");
			}else {
				return 0;
			}
			
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			
			
			
			// --------------------------------------------		재고 계산해서 매입한 원자재 재고 수정	
			sql = "UPDATE part SET part_stock = part_stock + ? "
					+ " WHERE partNo = ? ";
			
			pstmt2 = conn.prepareStatement(sql);		
			
			pstmt2.setInt(1, buydto.getBuy_qty());
			pstmt2.setString(2, buydto.getPartNo());
			
			pstmt2.executeUpdate();
			
			
			pstmt2.close();
			pstmt2 = null;
			
			
			// --------------------------------------------	 해당 매입처에서 상호명, 주소 가져오기		
			
			sql = "SELECT buy_No_seq.CURRVAL FROM dual ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b_no = rs.getString(1);

			}
			
		
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;


			// --------------------------------------------		세금 계산서 발행
			sql = "INSERT INTO BuyTaxBill (btb_No, buy_No, btb_sNo, btb_sBoss, btb_saddr, btb_Date, btb_pname, btb_price, btb_tax, "
					+ " btb_qty, btb_pprice, btb_total, btb_misu, btb_con ) "
					+ " VALUES ('TB'||TO_CHAR(btb_No_seq.NEXTVAL), 'B_'||?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, '정산완료' ) ";
						 
						
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt2.setString(1, b_no); //매입코드
			pstmt2.setString(2, sname); //상호명
			pstmt2.setString(3, sboss); //대표명
			pstmt2.setString(4, saddr); // 주소
			pstmt2.setString(5, buydto.getBuy_Date()); // 매입일자
			pstmt2.setString(6, pname); //재료명
			pstmt2.setInt(7, price * buydto.getBuy_qty()); //매입금
			pstmt2.setInt(8, (int)((price * buydto.getBuy_qty()) * 0.1)); //세액
			pstmt2.setInt(9, buydto.getBuy_qty()); //수량
			pstmt2.setInt(10, price); //단가 - 재료 단가
			pstmt2.setInt(11, (int)((price * buydto.getBuy_qty()) * 0.1)); //합계금액
	
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
			conn.rollback();
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

	
	
	
	//신규 원자재 등록하기
	@Override
	public int insertPart(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		try {
			
			sql ="INSERT INTO part (partNo, part_name, part_price, part_stock) "
					+ " VALUES (?, ?, ?, 0) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getPartNo());
			pstmt.setString(2, buydto.getPart_name());
			pstmt.setInt(3, buydto.getPart_price());
			
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
	
	
	
	
	// 발주리스트 전체조회
	@Override
	public List<BuyDTO> listBuy() {
		List<BuyDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT buy_No, stateNo, buy.partNo, part.part_name, buy_Date, buy_qty, buy_price, shop_No "
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
				buydto.setBuy_Date(rs.getDate("buy_Date").toString());
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


	
	
	
	
	//------------------------------------------------------------------------------
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
			pstmt.close();
			pstmt = null;
			
			// -----------------------
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL , ?, ?, '251', ?, ?, '', '미승인', ?) ";
			
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


	

	//등록전표조회 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Override
	public List<AccDTO> listAccBuy() {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			
			/////////////////////차변 대변 그거 만들어달라고 해야함
			sql = "SELECT stateNo, c.t_account , accountNo, b.asub_name, amount, cancellation, stateCon, stateDate, empNo "
					+ " FROM accounting a "
					+ " JOIN accountsub b ON a.accountSubNo = b.accountSubNo "
					+ " JOIN CATEGORY c ON c.categNo = b.categNo "
					+ " WHERE A.accountSubNo = '153' OR A.accountSubNo = '251' ";
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AccDTO accdto = new AccDTO();
				
				
				
				accdto.setStateNo(rs.getInt("StateNo"));
				accdto.setT_account(rs.getString("t_account"));
				accdto.setAccountNo(rs.getString("accountNo"));
				accdto.setAsub_name(rs.getString("asub_name"));
				accdto.setAmount(rs.getInt("amount"));
				accdto.setCancellation(rs.getString("cancellation"));
				accdto.setStateCon(rs.getString("stateCon"));
				accdto.setStateDate(rs.getDate("stateDate").toString());
				accdto.setEmpNo(rs.getString("empNo"));
				
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


	//전표상태 취소 !!!!!!!!!!!!!!!!!!!!!
	@Override
	public int deleteAccBuy(AccDTO accdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		
		try {
			
			sql = "DELETE FROM accounting "
					+ " WHERE StateNo = ? AND stateCon = '미승인' AND (accountSubNo = '153' OR accountSubNo = '251') ";
			
			
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


	
	/////////////////////////////반품 //////////////////////
	// 반품 테이블에 데이터 등록 -> 재고 테이블에서 신청한 자재만큼 빠짐 -> 구매테이블에서 매입수량 0으로 변경 -> 전표에 반품으로 찍혀서 작성되어야함
	// 
	@Override
	public int insertBanpum(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql;
		int result = 0;
		
		int qty = 0;
		String pcode, accn;
		int bp = 0;
		
		try {
			
			// --------------------------------------------	 매입 신청수량 가져오기
			conn.setAutoCommit(false);
			
			sql = "SELECT buy_qty, partNo, buy_price FROM buy "
					+ " WHERE buy_No = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydto.getBuy_No());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qty = rs.getInt("buy_qty");
				pcode = rs.getString("partNo");
				bp = rs.getInt("buy_price");
			}else {
				return 0;
			}
		
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			
			
			// --------------------------------------------	 반품 테이블에 추가	
			sql = "INSERT INTO banpum(ban_No, ban_Date, ban_qty, ban_Finish, ban_Memo)"
					+ " VALUES (?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getBuy_No());
			pstmt.setString(2, buydto.getBan_Date());
			pstmt.setInt(3, qty);
			pstmt.setString(4, buydto.getBan_Date());  // 하루 추가하기? +1 일
			pstmt.setString(5, buydto.getBan_Memo());

			pstmt.executeUpdate();

			pstmt.close();
			pstmt = null;
			
			
			// --------------------------------------------	 재고 테이블에서 수량 변경
			sql = "UPDATE part SET part_stock = part_stock - ? "
					+ " WHERE partNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qty);
			pstmt.setString(2, pcode);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
			
			
			
			// --------------------------------------------	 전표에 반품? 신청으로 등록!!
			
			
			sql = "SELECT accountNo FROM accounting "
					+ " WHERE stateNo = (SELECT stateNo FROM buy WHERE buy_No = ? ) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getBuy_No());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				accn = rs.getString("accountNo");
			}else {
				return 0;
			}
		
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;


			
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, ?, ?, '153', ?, ?, '', '승인', ?) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getEmpNo());
			pstmt.setString(2, accn);
			pstmt.setInt(3, bp * -1 );
			pstmt.setString(4, buydto.getBan_Memo());
			pstmt.setString(5, buydto.getBan_Date());
			
			
			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL , ?, ?, '251', ?, ?, '', '승인', ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getEmpNo());
			pstmt.setString(2, accn);
			pstmt.setInt(3, bp * -1 );
			pstmt.setString(4, buydto.getBan_Memo());
			pstmt.setString(5, buydto.getBan_Date());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;


			
			// --------------------------------------------	 구매 테이블에 매입수량 0으로 변경
			sql = "UPDATE buy SET buy_qty = 0 , buy_price = 0 "
					+ " WHERE buy_No = ? AND partNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buydto.getBuy_No());
			pstmt.setString(2, pcode);
			
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			
			// --------------------------------------------	 세금 계산서 테이블에 정보 변경
			sql = "";
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.executeUpdate();
			
			

			result = 1;
			
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
			e.printStackTrace();
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


	
	//반품 리스트 조회 
	@Override
	public List<BuyDTO> listBanpum() {
		List<BuyDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT ban_No, ban_date, ban_qty, ban_finish, ban_memo "
					+ " FROM banpum ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BuyDTO buydto = new BuyDTO();
				
				buydto.setBan_No(rs.getString("ban_No"));
				buydto.setBan_Date(rs.getDate("ban_date").toString());
				buydto.setBan_qty(rs.getInt("ban_qty"));
				buydto.setBan_Finish(rs.getDate("ban_finish").toString());
				buydto.setBan_Memo(rs.getString("ban_memo"));

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


	
	
	
	// 매입요청 조회하기 
	@Override
	public List<BuyDTO> applyList() {
		List<BuyDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		
		try {
			sql = "SELECT partofferno, partNo, part_name, qty, offer_date "
					+ " FROM partoffer ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BuyDTO buydto = new BuyDTO();
				
				buydto.setPartOfferNo(rs.getInt("partofferno"));
				buydto.setPartNo(rs.getString("partNo"));
				buydto.setPart_name(rs.getString("part_name"));
				buydto.setQty(rs.getInt("qty"));
				buydto.setOffer_date(rs.getDate("offer_date").toString());
				
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


	// 매입요청 삭제하기
	@Override
	public int deleteApply(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		try {
			
			sql = "DELETE FROM partoffer "
					+ " WHERE PartOfferNo = ?  ";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, buydto.getPartOfferNo());
			
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


	
	
	
	@Override
	public List<BuyDTO> listBuyTaxBill() {
		// TODO Auto-generated method stub
		return null;
	}


	
	//동일 전표등록 방지용 전표일련번호 검색하기
	@Override
	public int searchBuyState(BuyDTO buydto) throws SQLException {
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		int result = 0;
		String sql;
		
		try {
			
			sql = "SELECT (SELECT stateNo FROM buy WHERE stateNo = ? ) FROM dual ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buydto.getStateNo());
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //처음 등록하면 발주 전표라면 0
				result = 1;
			} 
 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
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
		
		return result;
	}

}




