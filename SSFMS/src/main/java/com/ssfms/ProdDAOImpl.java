package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class ProdDAOImpl implements ProdDAO {
	private Connection conn = DBConn.getConnection();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


	@Override
	public void reg_product(ProductDTO pdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "INSERT INTO product(productNo, product_name, cost, price, stock) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, pdto.getProductNo());
			pstmt.setString(2, pdto.getProduct_name());
			pstmt.setInt(3, pdto.getCost());
			pstmt.setInt(4, pdto.getPrice());
			pstmt.setInt(5, pdto.getStock());
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public void del_product(String productNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "DELETE FROM PRODUCT WHERE productNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productNo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public void using_part(ProdDTO pdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {

			sql = "INSERT INTO STOCK(StockNo, prodNo, partNo, pStock, use, nStock, date)"
					+ "VALUES(STOCK_seq.nextval, ?, ?, ?, ?, ?, SYSDATE )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdto.getProdNo()); // 생산번호
			pstmt.setString(2, pdto.getPartNo()); // 부품코드
			int stock =readPart(pdto.getPartNo()).getPart_stock();
			pstmt.setInt(3, stock); // 기존 재고량
			pstmt.setInt(4, pdto.getQty()); // 사용량
			pstmt.setInt(5, stock-pdto.getQty()); // 현재 재고량
			if(stock-pdto.getQty()<0) {
				System.out.println("현재 재고량보다 많이 사용할수 없습니다.");
				return;
			}
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "UPDATE part SET part_stock = part_stock - ? WHERE partNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stock-pdto.getQty());
			pstmt.setString(2, pdto.getPartNo());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}

	}

	@Override
	public ProdDTO producing(ProdDTO pdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "INSERT INTO ";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public int productStock(String productNo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public BuyDTO readPart(String partNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql;
		BuyDTO bdto = new BuyDTO();
		
		try {
			sql="SELECT partNo, part_name, part_price, part_stock FROM PART WHERE partNo =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, partNo);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				bdto.setPartNo(partNo);
				bdto.setPart_name(rs.getString("part_name"));
				bdto.setPart_price(rs.getInt("part_price"));
				bdto.setPart_stock(rs.getInt("part_stock"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}

}
