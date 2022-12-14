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

public class AccDAOImpl implements AccDAO {
	private Connection conn = DBConn.getConnection();

//==================================================================================
	// 전표관련
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
			if (e.getErrorCode() == 1) {
				System.out.println("동일한 전표번호가 존재합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
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
	public int updateAccount(AccDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE accounting SET empNo=?, accountNo=?, accountsubNo=?, amount=?, "
					+ " detail=?, cancellation=?, statecon=? " + " WHERE StateNo=? ";

			pstmt = conn.prepareCall(sql);

			pstmt.setString(1, dto.getEmpNo());
			pstmt.setString(2, dto.getAccountNo());
			pstmt.setString(3, dto.getAccountSubNo());
			pstmt.setInt(4, dto.getAmount());
			pstmt.setString(5, dto.getDetail());
			pstmt.setString(6, dto.getCancellation());
			pstmt.setString(7, dto.getStateCon());
			pstmt.setInt(8, dto.getStateNo());

			pstmt.executeUpdate();

			result = 1;

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
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
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();

			} catch (Exception e2) {
				e.printStackTrace();
			}

		} finally {
			try {
				conn.setAutoCommit(true);

			} catch (Exception e2) {

			}
		}

		return result;
	}

//==================================================================================
	// 전표조회
	@Override
	public AccDTO readAccount(int stateNo) throws SQLException {
		AccDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = " SELECT stateNo, empNo, accountNo, accountSubNo, amount, detail, cancellation, stateCon, stateDate "
					+ "FROM accounting" + " WHERE stateNo = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stateNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
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

		if (dto == null) {
			return null;
		}

		return dto;
	}

	@Override
	public List<AccDTO> listAccount() throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT stateNo, t_account, empNo, accountNo, asub_name, amount, detail, cancellation, stateCon, "
					+ " TO_char(stateDate,'YYYY-MM-DD')stateDate, dep, rank, name FROM State_view";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setT_account(rs.getString("t_account"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAsub_name(rs.getString("asub_name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
				dto.setName(rs.getString("name"));

				list.add(dto);
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

	@Override
	public List<AccDTO> listAccount_emp(String empNo) throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT stateNo, t_account, empNo, accountNo, asub_name, amount, detail, cancellation, stateCon, "
					+ " TO_char(stateDate,'YYYY-MM-DD')stateDate, dep, rank, name FROM State_view "
					+ " WHERE empNo = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, empNo);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setT_account(rs.getString("t_account"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAsub_name(rs.getString("asub_name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
				dto.setName(rs.getString("name"));

				list.add(dto);
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

	@Override
	public List<AccDTO> listAccount_subNo(String asub_name) throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT stateNo, t_account, empNo, accountNo, asub_name, amount, detail, cancellation, stateCon, "
					+ " TO_char(stateDate,'YYYY-MM-DD')stateDate, dep, rank, name FROM State_view "
					+ " WHERE asub_name = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, asub_name);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setT_account(rs.getString("t_account"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAsub_name(rs.getString("asub_name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
				dto.setName(rs.getString("name"));

				list.add(dto);
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

//==================================================================================	
	// 승인관련
	@Override
	public List<AccDTO> listNapproval() throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT stateNo, t_account, empNo, accountNo, asub_name, amount, detail, cancellation, stateCon, "
					+ " TO_char(stateDate,'YYYY-MM-DD')stateDate, dep, rank, name FROM State_view "
					+ " WHERE StateCon = '미승인' ";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setT_account(rs.getString("t_account"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAsub_name(rs.getString("asub_name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
				dto.setName(rs.getString("name"));

				list.add(dto);
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

	// 승인전표리스트
	public List<AccDTO> listapproval() throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT stateNo, t_account, empNo, accountNo, asub_name, amount, detail, cancellation, stateCon, "
					+ " TO_char(stateDate,'YYYY-MM-DD')stateDate, dep, rank, name FROM State_view "
					+ " WHERE StateCon = '승인' ";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setT_account(rs.getString("t_account"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAsub_name(rs.getString("asub_name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
				dto.setName(rs.getString("name"));

				list.add(dto);
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
	/*
	 * // 한번에 승인 public List<AccDTO> listapprovaALL() throws SQLException {
	 * PreparedStatement pstmt = null; String sql; conn.setAutoCommit(false);
	 * ResultSet rs = null; try { sql =
	 * "UPDATE accounting set stateCon = '승인' WHERE stateCon = '미승인' ";
	 * 
	 * pstmt = conn.prepareStatement(sql); for (AccDTO dto : ) {
	 * 
	 * pstmt.executeUpdate(); pstmt.close();
	 * 
	 * } pstmt.close();
	 * 
	 * conn.commit(); conn.setAutoCommit(true); } catch (
	 * 
	 * SQLException e) { conn.rollback(); e.printStackTrace(); } catch (Exception e)
	 * { e.printStackTrace(); } finally { if (pstmt != null) { try { pstmt.close();
	 * } catch (Exception e) { }
	 * 
	 * } } }
	 */

//==================================================================================
	// 계좌등록
	@Override
	public int insertAccountNo(AccDTO adto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO account (accountNo, bankName, accountNum, name,busAmount,balance)  "
					+ " VALUES(?,?,?,?,?,?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adto.getAccountNo());
			pstmt.setString(2, adto.getBankName());
			pstmt.setString(3, adto.getAccountNum());
			pstmt.setString(4, adto.getName());
			pstmt.setInt(5, adto.getBusAmount());
			pstmt.setInt(6, adto.getBalance());

			pstmt.executeUpdate();
			result = 1;

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("동일한 계좌번호가 존재합니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
			e.printStackTrace();

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
					e2.printStackTrace();

				}
			}
		}

		return result;
	}

	@Override
	public int updateAccountNo(AccDTO adto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = " UPDATE account SET bankName=?, accountNum=?, name =? ,busAmount=?,balance=? "
					+ " WHERE accountNo =? ";

			pstmt = conn.prepareCall(sql);

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, adto.getBankName());
			pstmt.setString(2, adto.getAccountNum());
			pstmt.setString(3, adto.getName());
			pstmt.setInt(4, adto.getAmount());
			pstmt.setInt(5, adto.getBalance());
			pstmt.setString(6, adto.getAccountNo());
			pstmt.executeUpdate();
			result = 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return result;
	}

	@Override
	public int deleteAccountNo(int accountNo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "DELETE FROM account WHERE accountNo = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, accountNo);

			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (Exception e2) {
				e.printStackTrace();
			}

		}

		return result;
	}

	@Override
	public AccDTO readAccountNO(int setAccountNo) throws SQLException {
		AccDTO adto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = " SELECT account accountNo, bankName, accountNum, name,busAmount,balance " + "FROM account"
					+ " WHERE setAccountNo = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, setAccountNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				adto = new AccDTO();

				adto.setStateNo(rs.getInt("setAccountNo"));
				
				pstmt.setString(1, adto.getAccountNo());
				pstmt.setString(2, adto.getBankName());
				pstmt.setString(3, adto.getAccountNum());
				pstmt.setString(4, adto.getName());
				pstmt.setInt(5, adto.getAmount());
				pstmt.setInt(6, adto.getBalance());
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

		if (adto == null) {
			return null;
		}

		return adto;
	}

	@Override
	public List<AccDTO> listAccountNo() throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT accountNo, bankName, accountNum, name,busAmount,balance FROM account";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO adto = new AccDTO();

				adto.setAccountNo(rs.getString("accountNo"));
				adto.setBankName(rs.getString("bankName"));
				adto.setAccountNum(rs.getString("accountNum"));
				adto.setName(rs.getString("name"));
				adto.setBusAmount(rs.getInt("busAmount"));
				adto.setBalance(rs.getInt("balance"));

				list.add(adto);
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

//==================================================================================
	// 계정과목 관련
	@Override
	public int insertAccSub(AccDTO sdto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO accountSub (accountSubNo, Asub_name , categNo) " + " VALUES(?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sdto.getAccountSubNo());
			pstmt.setString(2, sdto.getAsub_name());
			pstmt.setString(3, sdto.getCategNo());

			pstmt.executeUpdate();
			result = 1;

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("이미 등록된 계정코드 입니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();

				}
			}
		}

		return result;
	}

	@Override
	public int updateAccSub(AccDTO sdto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE accountSub SET Asub_name=?, categNo=?  " + "WHERE AccountSubNo=? ";

			pstmt = conn.prepareCall(sql);

			pstmt.setString(1, sdto.getAsub_name());
			pstmt.setString(2, sdto.getCategNo());
			pstmt.setString(3, sdto.getAccountSubNo());

			pstmt.executeUpdate();

			result = 1;

		} catch (Exception e) {
			e.printStackTrace();
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
	public int deleteAccSub(int accountSubNo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "DELETE FROM accountSub WHERE accountSubNo = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, accountSubNo);

			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (Exception e2) {
				e.printStackTrace();
			}

		}

		return result;
	}

	@Override
	public AccDTO readAccSub(int accountSubNo) throws SQLException {
		AccDTO sdto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = " SELECT accountSubNo, asub_name, categNo " + "FROM accountSub" + " WHERE accountSubNo = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountSubNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sdto = new AccDTO();

				pstmt.setString(1, sdto.getAccountSubNo());
				pstmt.setString(2, sdto.getAsub_name());
				pstmt.setString(3, sdto.getCategNo());

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

		if (sdto == null) {
			return null;
		}

		return sdto;
	}

	@Override
	public List<AccDTO> listAccSub() throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT accountSubNo, asub_name, categNo FROM accountSub ";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO sdto = new AccDTO();

				sdto.setAccountSubNo(rs.getString("accountSubNo"));
				sdto.setAsub_name(rs.getString("asub_name"));
				sdto.setCategNo(rs.getString("categNo"));

				list.add(sdto);
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

	// 승인
	@Override
	public void ListApproval(List<AccDTO> list) throws SQLException {
		for (AccDTO dto : list) {
			PreparedStatement pstmt = null;
			String sql;
			try {
				sql = " UPDATE accounting SET stateCon = '승인' WHERE stateNo = ? ";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getStateNo());
				pstmt.executeUpdate();

				System.out.println("전표 " + dto.getStateNo() + "번이 승인 되었습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null) {

					try {
						pstmt.close();
					} catch (Exception e2) {

					}
				}
			}

		}

	}

	// 월별 매출
	@Override
	public List<AccDTO> sblist(int month) throws SQLException {
		List<AccDTO> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT * FROM state_view "
					+ " WHERE TO_CHAR(sysdate, 'YYYY')= TO_CHAR(statedate, 'YYYY') AND TO_CHAR(statedate, 'MM') = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, month);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setT_account(rs.getString("t_account"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAsub_name(rs.getString("asub_name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
				dto.setName(rs.getString("name"));

				list.add(dto);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
