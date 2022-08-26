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

public class EmpDAOImpl implements EmpDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertEmp(EmpDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		
		try {
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
	public List<EmpDTO> listEmp() {
		List<EmpDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			// 전체 리스트
			// SELECT 컬럼, 컬럼 FROM 테이블
			sql  = "SELECT hak, name, TO_CHAR(birth, 'YYYY-MM-DD') birth, "
					+ " kor, eng, mat, (kor+eng+mat) tot, (kor+eng+mat)/3 ave, "
					+ " RANK() OVER( ORDER BY (kor+eng+mat) DESC ) rank "
					+ " FROM emp";
			
			pstmt = conn.prepareStatement(sql);
			
			// ? 가 없으므로 setter가 없음
			
			rs = pstmt.executeQuery();
			
			// score 테이블의 모든 레코드를 읽어 List 객체에 저장
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				/*
				dto.setEmpNo(sql);
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAve(rs.getInt("ave"));
				dto.setRank(rs.getInt("rank"));
				*/
				
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
	
}

