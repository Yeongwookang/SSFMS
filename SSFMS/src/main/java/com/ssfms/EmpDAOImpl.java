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

// import oracle.jdbc.OracleTypes;

public class EmpDAOImpl implements EmpDAO {
	private Connection conn = DBConn.getConnection();

	// 사원리스트 입력
	@Override
	public int insertEmp(EmpDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			conn.setAutoCommit(false);
			// 사원리스트 등록
			sql = "INSERT INTO emp(empNo, pwd, name, tel, rrn, email, addr, edu, account, hire_class) "
					+ "VALUES ( test_seq.NexTval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 경력사항 등록
			sql = "INSERT INTO career(empNo, carNo, div, Car_date, note, depNo, rankNo) "
					+ "VALUES ( test_seq.currval, care_seq.nextval, '입사', sysdate, ?, ?, '01000')";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getcNote());
			pstmt.setString(2, dto.getDepNo());

			result += pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 지금 입력한 사번 출력하기
			sql = "SELECT test_seq.currval FROM dual ";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setEmpNo(rs.getString(1));
			}

			conn.commit();

		} catch (SQLIntegrityConstraintViolationException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if (e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("사번 중복으로 등록이 불가능합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;

		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}
			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			throw e;

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			throw e;

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

			conn.setAutoCommit(true);

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
			sql = "UPDATE emp SET pwd=?, name=?, tel=?, rrn=?, email=?, " + "addr=?, edu=?, account=?, hire_class=?"
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

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLDataException e) {
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLException e) {
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

	// 사원리스트 출력
	@Override
	public List<EmpDTO> listEmp() {
		List<EmpDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "SELECT empNo, name, tel, email, addr, edu, account, hire_class FROM emp ORDER BY empNo";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
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
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

	// 경력사항 입력
	@Override
	public int insertCare(EmpDTO dto) throws SQLException {
		int result = 0;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "{ CALL insert_care(?, ?, ?, ?, ?) }";
			cstmt = conn.prepareCall(sql);

			cstmt.setString(1, dto.getcDiv());
			cstmt.setString(2, dto.getcNote());
			cstmt.setString(3, dto.getDepNo());
			cstmt.setString(4, dto.getRankNo());
			cstmt.setString(5, dto.getEmpNo());

			cstmt.executeUpdate();
			result = 1;

			// 지금 입력한 경력코드 출력하기
			sql = "SELECT care_seq.currval FROM dual ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setCarNo(rs.getString(1));
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if (e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("사번 중복으로 등록이 불가능합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;

		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}

	// 경력사항 수정
	@Override
	public int updateCare(EmpDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE career SET div=?, car_date=SYSDATE, Note=?, depNo=?, rankNo=?, empNo=? WHERE carNo = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getcDiv());
			pstmt.setString(2, dto.getcNote());
			pstmt.setString(3, dto.getDepNo());
			pstmt.setString(4, dto.getRankNo());
			pstmt.setString(5, dto.getEmpNo());
			pstmt.setString(6, dto.getCarNo());

			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLDataException e) {
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLException e) {
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

	// 경력사항 출력
	@Override
	public List<EmpDTO> listCare() {
		List<EmpDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = " SELECT  carNo, empNo, div, car_date, dep, rank"
					+ " FROM career c join department d ON c.depNo = d.depNo join rank r ON c.rankNo = r.rankNo";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDTO dto = new EmpDTO();

				dto.setCarNo(rs.getString("carNo"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setcDiv(rs.getString("div"));
				dto.setCar_date(rs.getString("car_date"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

	// 연봉 입력
	@Override
	public int insertAsal(EmpDTO adto) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql;

		try {
			sql = "INSERT INTO annual_salary(asalNo, sal_date, asal, empNo) "
					+ "VALUES (ann_seq.NEXTVAL, sysdate, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, adto.getAsal());
			pstmt.setString(2, adto.getEmpNo());

			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 지금 입력한 연봉코드 출력하기
			sql = "SELECT ann_seq.currval FROM dual ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				adto.setAsalNo(rs.getString(1));
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if (e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("학번 중복으로 등록이 불가능합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;

		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;

		} catch (SQLException e) {
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

	// 연봉 수정
	@Override
	public int updateAsal(EmpDTO adto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE annual_salary SET sal_date=SYSDATE, asal=?, empNo=? WHERE asalNo = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, adto.getAsal());
			pstmt.setString(2, adto.getEmpNo());
			pstmt.setString(3, adto.getAsalNo());

			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLDataException e) {
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLException e) {
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

	// 연봉 리스트출력
	@Override
	public List<EmpDTO> listAsal() {
		List<EmpDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "SELECT asalNo, sal_date, asal, empNo FROM annual_salary ORDER BY asalNo";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDTO dto = new EmpDTO();

				dto.setAsalNo(rs.getString("asalNo"));
				dto.setSal_date(rs.getString("sal_date"));
				dto.setAsal(rs.getInt("asal"));
				dto.setEmpNo(rs.getString("empNo"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

	// 급여 입력
	@Override
	public int insertSett(AccDTO accdto, EmpDTO empdto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "INSERT INTO settlement (settleNo, empNo, sal, tax, bonus, pay, pay_date)"
					+ "VALUES(sett_seq.NextVAl, ?, ?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, empdto.getEmpNo());
			pstmt.setInt(2, empdto.getSal());
			pstmt.setInt(3, empdto.getTax());
			pstmt.setInt(4, empdto.getBonus());
			pstmt.setInt(5, empdto.getPay());

			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 급여 : 차변
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, '1001', ?, '503', ?, ?||'급여', '', '미승인', SYSDATE)";
			pstmt = conn.prepareStatement(sql);

			// pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(1, accdto.getAccountNo());
			pstmt.setInt(2, empdto.getSal());
			pstmt.setString(3, accdto.getDetail());

			result += pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 상여금 : 차변
			if (empdto.getBonus() != 0) {
				sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
						+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, '1001', ?, '803', ?, ?||'상여금', '', '미승인', SYSDATE)";
				pstmt = conn.prepareStatement(sql);

				// pstmt.setString(1, empdto.getEmpNo());
				pstmt.setString(1, accdto.getAccountNo());
				pstmt.setInt(2, empdto.getBonus());
				pstmt.setString(3, accdto.getDetail());

				result += pstmt.executeUpdate();
				pstmt.close();
				pstmt = null;
			}

			// 보통예금 : 대변
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, '1001', ?, '1031', ?, ?||'보통예금', '', '미승인', SYSDATE)";
			pstmt = conn.prepareStatement(sql);

			// pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(1, accdto.getAccountNo());
			pstmt.setInt(2, accdto.getAmount());
			pstmt.setString(3, accdto.getDetail());

			result += pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 세금 : 대변
			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, '1001', ?, '517', ?, ?||'세금', '', '미승인', SYSDATE)";
			pstmt = conn.prepareStatement(sql);

			// pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(1, accdto.getAccountNo());
			pstmt.setInt(2, empdto.getTax());
			pstmt.setString(3, accdto.getDetail());

			result += pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 지금 입력한 급여코드 출력하기
			sql = "SELECT sett_seq.currval FROM dual ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				empdto.setSettleNo(rs.getString(1));
			}

			// 지금 입력한 전표코드 출력하기
			sql = "SELECT ACCOUNTING_SEQ.currval FROM dual ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				accdto.setStateNo(rs.getInt(1));
			}

			conn.commit();

		} catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if (e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("사번 중복으로 등록이 불가능합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			try {
				conn.rollback();
			} catch (Exception e2) {

			}

			throw e;

		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}
			try {
				conn.rollback();
			} catch (Exception e2) {

			}
			throw e;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {

			}
			throw e;

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			conn.setAutoCommit(true);
		}

		return result;
	}

	// 급여 등록 취소
	@Override
	public int deleteSett(AccDTO accdto, EmpDTO empdto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			conn.setAutoCommit(false);
			// 상여금 불러오기
			sql = "SELECT bonus FROM SETTLEMENT WHERE settleNo = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empdto.getSettleNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				empdto.setBonus(rs.getInt("bonus"));
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;

			// 승인여부 불러오기
			sql = "SELECT stateCon FROM accounting WHERE StateNo = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accdto.getStateNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				accdto.setStateCon(rs.getString("stateCon"));
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;

			// 미승인인 경우
			if (accdto.getStateCon().equals("미승인")) {
				sql = "DELETE FROM SETTLEMENT WHERE settleNo = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empdto.getSettleNo());
				result = pstmt.executeUpdate();

				pstmt.close();
				pstmt = null;

				sql = "DELETE FROM accounting WHERE StateNo = ? AND stateCon = '미승인'";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, accdto.getStateNo());
				result += pstmt.executeUpdate();

				pstmt.close();
				pstmt = null;

				sql = "DELETE FROM accounting WHERE StateNo = ? AND stateCon = '미승인'";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, accdto.getStateNo() + 1);
				result += pstmt.executeUpdate();

				pstmt.close();
				pstmt = null;

				sql = "DELETE FROM accounting WHERE StateNo = ? AND stateCon = '미승인'";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, accdto.getStateNo() + 2);
				result += pstmt.executeUpdate();

				// 상여금이 있는 경우
				if (empdto.getBonus() != 0) {
					pstmt.close();
					pstmt = null;

					sql = "DELETE FROM accounting WHERE StateNo = ? AND stateCon = '미승인'";

					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, accdto.getStateNo() + 3);
					result += pstmt.executeUpdate();

				}
				conn.commit();
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if (e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("사번 중복으로 등록이 불가능합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			try {
				conn.rollback();
			} catch (Exception e2) {

			}

			throw e;

		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}
			try {
				conn.rollback();
			} catch (Exception e2) {

			}
			throw e;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {

			}
			throw e;

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
			conn.setAutoCommit(true);
		}
		return result;
	}

	// 급여 리스트
	@Override
	public List<EmpDTO> listSett() {
		List<EmpDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "SELECT settleNo, empNo, sal, tax, bonus, pay, pay_date FROM settlement ORDER BY settleNo";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDTO dto = new EmpDTO();

				dto.setSettleNo(rs.getString("settleNo"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setSal(rs.getInt("sal"));
				dto.setTax(rs.getInt("tax"));
				dto.setBonus(rs.getInt("bonus"));
				dto.setPay(rs.getInt("pay"));
				dto.setPay_date(rs.getString("pay_date"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

	// 근태 입력
	@Override
	public int insertAtt(EmpDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			// sql = "SELECT*FROM (SELECT sTime FROM attendance WHERE empno = ? ORDER BY attno DESC) WHERE rownum =1";

			sql = "INSERT INTO attendance(attNo, empNo, sTime, eTime, note) "
					+ " VALUES (att_seq.NEXTVAL, ?, SYSDATE, '', ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getEmpNo());
			pstmt.setString(2, dto.getaNote());

			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;

			// 지금 입력한 근태코드 출력하기
			sql = "SELECT att_seq.currval FROM dual ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setAttNo(rs.getString(1));
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 제약 위반, NOT NULL 등의 제약 위반 - 무결성 제약 위반시 발생
			if (e.getErrorCode() == 1) { // 기본키 중복
				System.out.println("사번 중복으로 등록이 불가능합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;

		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;

		} catch (SQLException e) {
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

	// 근태 수정
	@Override
	public int updateAtt(EmpDTO dto) throws SQLException {
		int result = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql;

		try {
			/*
			sql = "SELECT eTime FROM attendance WHERE attno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAttNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.seteTime(rs.getString("eTime"));
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			*/

			//if (dto.geteTime().equals(null)) {
				sql = "UPDATE attendance SET empNo = ?, eTime = SYSDATE, note=? WHERE attNo = ?";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, dto.getEmpNo());
				pstmt.setString(2, dto.getaNote());
				pstmt.setString(3, dto.getAttNo());

				result = pstmt.executeUpdate();
			//} 

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLDataException e) {
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("날짜 입력 형식 오류 입니다.");
			} else {
				System.out.println(e.toString());
			}

			throw e;
		} catch (SQLException e) {
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

	// 근태 리스트
	@Override
	public List<EmpDTO> listAtt() {
		List<EmpDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "SELECT attNo, sTime, eTime, empNo, note FROM attendance ORDER BY attNo";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDTO dto = new EmpDTO();

				dto.setAttNo(rs.getString("attNo"));
				dto.setsTime(rs.getString("sTime"));
				dto.seteTime(rs.getString("eTime"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setaNote(rs.getString("note"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

	// 전표 등록
	@Override
	public int insertAccSett(AccDTO accdto, EmpDTO empdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;

		try {

			sql = "INSERT INTO accounting (stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate)"
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL, ?, ?, ?, ?, ?, '', '미승인', SYSDATE)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(2, accdto.getAccountNo());
			pstmt.setString(3, accdto.getAccountSubNo());
			pstmt.setInt(4, accdto.getAmount());
			pstmt.setString(5, accdto.getDetail());

			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {

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

	// 전표 수정
	@Override
	public int updateAccSett(AccDTO accdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;

		try {

			sql = "UPDATE accounting SET cancellation = 'O' WHERE StateNo = ? AND stateCon = '미승인'";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, accdto.getStateNo());

			result = pstmt.executeUpdate();

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

	// 급여 전표 리스트
	@Override
	public List<AccDTO> listAccSett() {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "SELECT stateNo, c.t_account , accountNo, b.asub_name, amount, cancellation, stateCon, stateDate, empNo "
					+ " FROM accounting a " + " JOIN accountsub b ON a.accountSubNo = b.accountSubNo "
					+ " JOIN CATEGORY c ON c.categNo = b.categNo "
					+ " WHERE A.accountSubNo = '503' OR A.accountSubNo = '1031' OR A.accountSubNo = '517'  OR A.accountSubNo = '803' "
					+ " ORDER BY stateNo";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

		}

		return list;
	}

	// 최신 부서, 직급 검색
	@Override
	public EmpDTO readMember(String empNo) {
		EmpDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT * FROM (" + " SELECT  carNo, empNo, div, car_date, dep, rank"
					+ " FROM career c join department d ON c.depNo = d.depNo join rank r ON c.rankNo = r.rankNo"
					+ " WHERE empNo = ?" + " ORDER BY carNo DESC" + " )WHERE rownum = 1";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new EmpDTO();

				dto.setCarNo(rs.getString("carNo"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setcDiv(rs.getString("div"));
				dto.setCar_date(rs.getString("car_date"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

		}
		return dto;
	}

	// 사번으로 정보 저장
	@Override
	public EmpDTO readEmp(String empNo) throws SQLException {
		EmpDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT empNo, pwd, name, tel, email, addr, edu, account, hire_class FROM emp WHERE empNo = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new EmpDTO();

				dto.setEmpNo(rs.getString("empNo"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setAddr(rs.getString("addr"));
				dto.setEdu(rs.getString("edu"));
				dto.setAccount(rs.getString("account"));
				dto.setHire_class(rs.getString("hire_class"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

		}
		return dto;
	}

}
