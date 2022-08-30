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

public class SalesDAOImpl implements SalesDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int estimateInsertSales(SalesDTO dto) throws SQLException {
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
			sql = "INSERT INTO sales (salesNo, stateNo, productNo, customer, sales, salesQty, dealDate)"
					+ " VALUES ('S_'||TO_CHAR(sales_no_seq.NEXTVAL), ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getStateNo());
			pstmt.setString(2, dto.getProductNo());
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
	public List<SalesDTO> listSales() {
		List<SalesDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT salesNo, stateNo, productNo, customer, sales, salesQty, dealDate FROM sales";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SalesDTO dto = new SalesDTO();

				dto.setSalesNo(rs.getString("salesNo"));
				dto.setStateNo(rs.getInt("stateNo"));
				dto.setProductNo(rs.getString("productNo"));
				dto.setCustomer(rs.getString("customer"));
				dto.setSales(rs.getInt("sales"));
				dto.setSalesQty(rs.getInt("salesQty"));
				dto.setDealDate(rs.getString("dealDate"));

				list.add(dto);
			}

		} catch (Exception e) {

		}

		return list;
	}

	@Override
	public int salesAccountInsert(EmpDTO empdto, AccDTO accdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;

		try {
			conn.setAutoCommit(false);
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, ?, ?, '412', ?, ?, '', '미승인', ?) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(2, accdto.getAccountNo());
			pstmt.setInt(3, accdto.getAmount());
			pstmt.setString(4, accdto.getDetail());
			pstmt.setString(5, accdto.getStateDate());

			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

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

		} catch (

		SQLIntegrityConstraintViolationException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}

			if (e.getErrorCode() == 1) {
				System.out.println("중복 데이터로 등록이 불가능합니다.");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			throw e;

		} catch (SQLDataException e) {

			try {
				conn.rollback();
			} catch (Exception e2) {
			}

			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류입니다.");
			} else {
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
			if (pstmt != null) {
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
	public List<AccDTO> listSalesAccountInsert() {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT stateNo, c.t_account , accountNo, b.asub_name, amount, cancellation, stateCon, stateDate, empNo "
					+ " FROM accounting a "
					+ " JOIN accountsub b ON a.accountSubNo = b.accountSubNo "
					+ " JOIN CATEGORY c ON c.categNo = b.categNo "
					+ " WHERE A.accountSubNo = '412' OR A.accountSubNo = '251' ";
			
			
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
