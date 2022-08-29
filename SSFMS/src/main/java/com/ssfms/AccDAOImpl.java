package com.ssfms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;


public class AccDAOImpl implements AccDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertAccount(AccDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO accounting (StateNo, empNo, accountNo, accountsubNo, amount, "
					+ " detail, cancellation, statecon, statedate)"
					+ " VALUES(ACCOUNTING_seq.nextval, ?,?,?,?,?,?,?, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmpNo());
			pstmt.setString(2, dto.getAccountNo());
			pstmt.setString(3, dto.getAccountSubNo());
			pstmt.setInt(4, dto.getAmount());
			pstmt.setString(5, dto.getDetail());
			pstmt.setString(6, dto.getCancellation());
			pstmt.setString(7, dto.getStateCon());
			
			pstmt.executeUpdate();
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
		}
		
		return result;
	}

	@Override
	public int updateAccount(AccDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO accounting (StateNo, empNo, accountNo, accountsubNo, amount, "
					+ " detail, cancellation, statecon, statedate)"
					+ " VALUES(ACCOUNTING_seq.nextval, ?,?,?,?,?,?,?, SYSDATE)";
			
			pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, dto.getEmpNo());
			pstmt.setString(2, dto.getAccountNo());
			pstmt.setString(3, dto.getAccountSubNo());
			pstmt.setInt(4, dto.getAmount());
			pstmt.setString(5, dto.getDetail());
			pstmt.setString(6, dto.getCancellation());
			pstmt.setString(7, dto.getStateCon());
			
			pstmt.executeUpdate();
			
			result = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
	public int deleteAccount(int stateNo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			conn.setAutoCommit(false);
			
			sql = "DELETE FROM accounting WHERE stateNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, stateNo);
			
			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			} 
			
		}
			
		
		return result;
	}
/*
	@Override
	public AccDTO readAccount (int stateNo) {
		AccDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = " SELECT stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate"
					+ "FROM accounting"
					+ "WHERE stateNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stateNo);
			
			rs = pstmt.executeQuery();
			
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
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return dto;
	}
*/
	@Override
	public List<AccDTO> listAccount() {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate "
					+ "FROM accounting";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			rs = pstmt.executeQuery();
			
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

			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}
	@Override
	public List<AccDTO> listAccount_emp(String empNo) {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate "
					+ "FROM accounting "
					+ "WHERE empNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			pstmt.setString(1, empNo);
			
			rs = pstmt.executeQuery();
			
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

			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}

	
	@Override
	public List<AccDTO> listAccount_subNo(String accountSubNo) {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "{ \"SELECT accounting (StateNo, empNo, accountNo, accountsubNo, amount, \"\r\n"
					+ "					+ \" detail, cancellation, statecon, statedate)\"\r\n"
					+ "					+ \" VALUES(ACCOUNTING_seq.nextval, ?,?,?,?,?,?,?, SYSDATE)\";\r\n"
					+ "			 }";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			rs = pstmt.executeQuery();
			
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

			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			
		}
		
		return list;
	}

	@Override
	public AccDTO readAccount(String stateNo) {
		// TODO Auto-generated method stub
		return null;
	}

}