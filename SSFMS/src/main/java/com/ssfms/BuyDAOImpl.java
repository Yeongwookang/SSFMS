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
	public int insertBuy(BuyDTO buydto) throws SQLException {
		return 0;
		
	}

	
	

	@Override
	public int updateBuy(BuyDTO buydto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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

	
	
	

	
}

