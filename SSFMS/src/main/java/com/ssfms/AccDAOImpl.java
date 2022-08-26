package com.ssfms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.List;

import com.util.DBConn;

public class AccDAOImpl implements AccDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertAccount(AccDTO dto) throws SQLException {
		int result = 0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			sql = "{ CALL accounting(?, ?, ?, ?, ?, ?, ?, ?)} ";
			
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dto.getEmpNo());
			cstmt.setString(2, dto.getAccountNo());
			cstmt.setString(3, dto.getAccountSubNo());
			cstmt.setString(4, dto.getCategNo());
			cstmt.setString(5, dto.getAmount());
			cstmt.setString(6, dto.getDetail());
			cstmt.setString(7, dto.getCancellation());
			cstmt.setString(8, dto.getStateCon());
			
			cstmt.executeUpdate();
			result = 1;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode() == 1 ) {
				System.out.println("동일한 전표번호가 존재합니다.");
			} else if(e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			
			throw e;
			
		} catch (SQLException e) {	
			e.printStackTrace();
			throw e;
			
		} finally {
			if(cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					
				}
			}
		}
		
		return result;
	}

	@Override
	public int updateAccount(AccDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccount(AccDTO stateNum) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AccDTO readAccount(String stateNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccDTO> listAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccDTO> listAccount_emp(String empNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccDTO> listAccount_categ(String categName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccDTO> listAccount_subNo(String accountSubNo) {
		// TODO Auto-generated method stub
		return null;
	}

}