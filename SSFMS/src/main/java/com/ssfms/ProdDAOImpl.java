package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import com.util.DBConn;

public class ProdDAOImpl implements ProdDAO {
	private Connection conn = DBConn.getConnection();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public AccDTO prodstateView(AccDTO state) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

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
	public void using_part(BuyDTO bdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "UPDATE part SET part_stock = part_stock - ? WHERE partNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bdto.getPart_stock());
			pstmt.setString(2, bdto.getPartNo());
			pstmt.executeUpdate(sql);

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO productStock(String product_name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
