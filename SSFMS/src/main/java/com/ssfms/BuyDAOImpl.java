package com.ssfms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.util.DBConn;

public class BuyDAOImpl implements BuyDAO {
	private Connection conn = DBConn.getConnection();

	
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
	public int deleteShop(BuyDTO buydto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}

