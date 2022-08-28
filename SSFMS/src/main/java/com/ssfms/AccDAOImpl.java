package com.ssfms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class AccDAOImpl implements AccDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertAccount(AccDTO dto) throws SQLException {
		int result = 0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			sql = "{ CALL insertAccount(?, ?, ?, ?, ?, ?, ?)} ";
			
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dto.getEmpNo());
			cstmt.setString(2, dto.getAccountNo());
			cstmt.setString(3, dto.getAccountSubNo());
			cstmt.setInt(4, dto.getAmount());
			cstmt.setString(5, dto.getDetail());
			cstmt.setString(6, dto.getCancellation());
			cstmt.setString(7, dto.getStateCon());
			
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
			
			
			
		} catch (SQLException e) {	
			e.printStackTrace();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
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
		int result = 0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			sql = "{ CALL updateAccount(?,?,?,?,?,?,?) }";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, dto.getEmpNo());
			cstmt.setString(2, dto.getAccountNo());
			cstmt.setString(3, dto.getAccountSubNo());
			cstmt.setInt(4, dto.getAmount());
			cstmt.setString(5, dto.getDetail());
			cstmt.setString(6, dto.getCancellation());
			cstmt.setString(7, dto.getStateCon());
			
			cstmt.executeUpdate();
			
			result = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
	public int deleteAccount(int stateNo) throws SQLException {
		int result = 0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			sql = "{ CALL deleteAccount(?) }";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, stateNo);
			
			cstmt.executeUpdate();
			
			result = 1;
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 20100) {
				System.out.println("등록된 자료가 아닙니다.");
			} else {
				e.printStackTrace();
			}
			
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
	public AccDTO readAccount(String stateNum) {
		AccDTO dto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "{ CALL readAccount(?,?) }";
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2, stateNum);
			
			cstmt.executeUpdate();
			
			rs = (ResultSet)cstmt.getObject(1);
			
			if(rs.next()) {
				dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAccountSubNo(rs.getString("accountSubNo"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));	
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
			if(cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return dto;
	}

	@Override
	public List<AccDTO> listAccount() {
		List<AccDTO> list = new ArrayList<>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "{ CALL listAccount(?)}";
			
cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstmt.executeUpdate();
			
			rs = (ResultSet)cstmt.getObject(1);
			
			while(rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAccountSubNo(rs.getString("accountSubNo"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));	
				
				list.add(dto);
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

			if(cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}
	@Override
	public List<AccDTO> listAccount_emp(String empNo) {
		List<AccDTO> list = new ArrayList<>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "{ CALL searchempNoAcc(?,?) }";
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2, empNo);
			
			cstmt.executeUpdate();
			
			rs = (ResultSet)cstmt.getObject(1);
			
			while(rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAccountSubNo(rs.getString("accountSubNo"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));	
				
				list.add(dto);
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

			if(cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}

	
	@Override
	public List<AccDTO> listAccount_subNo(String accountSubNo) {
		List<AccDTO> list = new ArrayList<>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "{ CALL searchcategNameAcc(?,?) }";
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2, accountSubNo);
			
			cstmt.executeUpdate();
			
			rs = (ResultSet)cstmt.getObject(1);
			
			while(rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAccountSubNo(rs.getString("accountSubNo"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));	
				
				list.add(dto);
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

			if(cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}

}