package com.ssfms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class EmpDAOImpl implements EmpDAO {
	private Connection conn = DBConn.getConnection();

	// 사원리스트 입력
	@Override
	public int insertEmp(EmpDTO dto) throws SQLException {
		int result = 0;
		// PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		String sql;
		
		
		try {
			/*
			sql = "INSERT INTO emp(empNo, pwd, name, tel, rrn, email, addr, edu, account, hire_class) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getEmpNo());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getRrn());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getAddr());
			pstmt.setString(8, dto.getEdu());
			pstmt.setString(9, dto.getAccount());
			pstmt.setString(10, dto.getHire_class());
			
			result = pstmt.executeUpdate();
			*/
			
			sql = "{ CALL insert_emp(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, dto.getPwd());
			cstmt.setString(2, dto.getName());
			cstmt.setString(3, dto.getTel());
			cstmt.setString(4, dto.getRrn());
			cstmt.setString(5, dto.getEmail());
			cstmt.setString(6, dto.getAddr());
			cstmt.setString(7, dto.getEdu());
			cstmt.setString(8, dto.getAccount());
			cstmt.setString(9, dto.getHire_class());
			
			cstmt.executeUpdate();
			result =1;
			
		}catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if(e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("사번 중복으로 등록이 불가능합니다.");
			} else if(e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			
			throw e;
			
		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if(e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
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

	// 사원리스트 수정
	@Override
	public int updateEmp(EmpDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		
		try {
			sql = "UPDATE emp SET pwd=?, name=?, tel=?, rrn=?, email=?, "
					+ "addr=?, edu=?, account=?, hire_class=?"
					+ "WHERE empNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getRrn());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getAddr());
			pstmt.setString(7, dto.getEdu());
			pstmt.setString(8, dto.getAccount());
			pstmt.setString(9, dto.getHire_class());
			pstmt.setString(10, dto.getEmpNo());
			
			result = pstmt.executeUpdate();
			
		}catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			
			throw e;
		} catch (SQLDataException e) {
			if(e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}
			
			throw e;
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
	
	// 사원리스트 출력
	@Override
	public List<EmpDTO> listEmp() {
		List<EmpDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql  = "SELECT empNo, name, tel, email, addr, edu, account, hire_class FROM emp";
			
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				
				dto.setEmpNo(rs.getString("empNo"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setAddr(rs.getString("addr"));
				dto.setEdu(rs.getString("edu"));
				dto.setAccount(rs.getString("account"));
				dto.setHire_class(rs.getString("hire_class"));
			
				
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
	public int insertCare(EmpDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCare(EmpDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmpDTO> listCare() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 연봉 입력
	@Override
	public int insertAsal(EmpDTO adto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		
		try {
			sql = "INSERT INTO annual_salary(asalNo, sal_date, asal, empNo) "
					+ "VALUES (?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, adto.getAsalNo());
			pstmt.setString(2, adto.getSal_date());
			pstmt.setInt(3, adto.getAsal());
			pstmt.setString(4, adto.getEmpNo());
			
			result = pstmt.executeUpdate();
			
		}catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if(e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("학번 중복으로 등록이 불가능합니다.");
			} else if(e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			
			throw e;
			
		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if(e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}
			
			throw e;
			
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
	public int updateAsal(EmpDTO adto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmpDTO> listAsal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSett(EmpDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSett(EmpDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmpDTO> listSett() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertAtt(EmpDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAtt(EmpDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmpDTO> listAtt() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

