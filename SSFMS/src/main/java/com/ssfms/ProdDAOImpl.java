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
			sql = "UPDATE part SET part_stock = part_stock - ? WHERE partNo = ?";
			sql = "INSERT INTO STOCK(StockNo, prodNo, partNo, pStock, use, nStock, date)"
					+ "VALUES(STOCK_seq.nextval, ?, ?, ?, ?, ?, SYSDATE )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdto.getProdNo()); // 생산번호
			pstmt.setString(2, pdto.getPartNo()); // 부품코드
			pstmt.setInt(3, pdto.partStock(pdto.getPartNo())); // 기존 재고량
			pstmt.setInt(4, pdto.getPart_stock()); // 사용량
			pstmt.setInt(5, pdto.partStock(pdto.getPartNo()) - pdto.getPart_stock()); // 현재 재고량
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdto.getPart_stock());
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

}
