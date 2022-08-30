package com.ssfms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.util.DBConn;


public class SalesDAOImpl implements SalesDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int estimateInsertSales(SalesDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSales(SalesDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SalesDTO estimateRead(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int salesInsert(SalesDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "INSERT INTO salesInsert (salesNo, stateNo, productCode, customer, sales, salesQty, dealDate)"
					+ " VALUES ('S'||TO_CHAR(sales_no_seq.NEXTVAL), ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getStateNo());
			pstmt.setString(2, dto.getProductCode());
			pstmt.setString(3, dto.getCustomer());
			pstmt.setInt(4, dto.getSales());
			pstmt.setInt(5, dto.getSalesQty());
			pstmt.setString(6, dto.getDealDate());	
			
			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {

			if (e.getErrorCode() == 1) {
				System.out.println("중복 데이터 등록은 불가합니다.");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("필수 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			throw e;

		} catch (SQLDataException e) {
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류입니다.");
				System.out.println("예시) 2001-01-01");
			} else {
				System.out.println(e.toString());
			}
			throw e;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}	

	@Override
	public SalesDTO salesRead(String salesNo) {
		SalesDTO dto = null;
		
		
		return null;
	}

	@Override
	public int salesAccountInsert(SalesDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public int taxBillInsert(SalesDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "INSERT INTO taxBill(taxBillNum, salesNo, companyName, name, address, busStatue, currDate, valueSupply, taxAmount,"
					+ "item, num, unitPrice, total, outAmount, note)"
					+ " VALUES(?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getTaxBillNum());
			pstmt.setInt(2, dto.getStateNo());
			pstmt.setString(3, dto.getCompanyName());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getBusStatue());
			pstmt.setInt(7, dto.getValueSupply());
			pstmt.setInt(8, dto.getTaxAmount());
			pstmt.setString(9, dto.getItem());
			pstmt.setInt(10, dto.getNum());
			pstmt.setInt(12, dto.getUnitPrice());
			pstmt.setInt(13, dto.getTotal());
			pstmt.setInt(14, dto.getOutAmount());
			pstmt.setString(14, dto.getNote());

		} catch (SQLIntegrityConstraintViolationException e) {

			if (e.getErrorCode() == 1) {
				System.out.println("중복 데이터 등록은 불가합니다.");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("필수 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			throw e;

		} catch (SQLDataException e) {
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류입니다.");
				System.out.println("예시) 2001-01-01");
			} else {
				System.out.println(e.toString());
			}
			throw e;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return result;
	}



}
