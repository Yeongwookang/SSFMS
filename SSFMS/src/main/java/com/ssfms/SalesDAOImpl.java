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
	public void estimateInsertSales(List<SalesDTO> list) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);
			
			for (SalesDTO dto : list) {
			sql = "INSERT INTO estimate (estimateNo, companyName, comRegiNo, tel, orderCom, name, orderComTel, eDate, "
					+ "productNo, productName, num, eCost, ePrice, note)"
					+ " VALUES (estimate_no_seq.NEXTVAL, '(주)쌍용IT제조', '021-3456-789', '02-123-4567', "
					+ "?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getOrderCom());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getOrderComTel());
			pstmt.setString(4, dto.getProductNo());
			pstmt.setString(5, dto.getProductName());
			pstmt.setInt(6, dto.getNum());
			pstmt.setInt(7, dto.geteCos());
			pstmt.setInt(8, dto.getePrice());
			pstmt.setString(9, dto.getNote());
			pstmt.executeUpdate();
			}
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SalesDTO> estimateRead() {
		List<SalesDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT estimateNo, companyName, comRegiNo, tel, orderCom, name, orderComTel, eDate, "
					+ "productNo, productName, num, eCost, ePrice, note FROM estimate";

			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SalesDTO dto = new SalesDTO();

				dto.setEstimateNo(rs.getString("estimateNo"));
				dto.setCompanyName(rs.getString("companyName"));
				dto.setComRegiNo(rs.getString("comRegiNo"));
				dto.setTel(rs.getString("tel"));
				dto.setOrderCom(rs.getString("orderCom"));
				dto.setName(rs.getString("name"));
				dto.setOrderComTel(rs.getString("orderComTel"));
				dto.seteDate(rs.getString("eDate"));
				dto.setProductNo(rs.getString("productNo"));
				dto.setProductName(rs.getString("productName"));
				dto.setNum(rs.getInt("num"));
				dto.seteCos(rs.getInt("eCost"));
				dto.setePrice(rs.getInt("ePrice"));
				dto.setNote(rs.getString("note"));

				list.add(dto);
			}

		} catch (Exception e) {

		}

		return list;
	}
	

	@Override
	public List<SalesDTO> orderRead() {
		List<SalesDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT orderNo, oDate, orderCom, oName, oTel, expDeliDate, companyName, comRegiNo, "
					+ "comAddress, comTel, productNo, productName, num, oCost, oPrice, total, note FROM orderBill";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SalesDTO dto = new SalesDTO();

				dto.setOrderNo(rs.getString("orderNo"));
				dto.setoDate(rs.getString("oDate"));
				dto.setOrderCom(rs.getString("orderCom"));
				dto.setoName(rs.getString("oName"));
				dto.setoTel(rs.getString("oTel"));
				dto.setExpDeliDate(rs.getString("expDeliDate"));
				dto.setCompanyName(rs.getString("companyName"));
				dto.setComRegiNo(rs.getString("comRegiNo"));
				dto.setComAddress(rs.getString("comAddress"));
				dto.setComTel(rs.getString("comTel"));
				dto.setProductNo(rs.getString("productNo"));
				dto.setProductName(rs.getString("productName"));
				dto.setOrderNum(rs.getInt("num"));
				dto.setoCost(rs.getInt("oCost"));
				dto.setoPrice(rs.getInt("oPrice"));
				dto.setTotal(rs.getInt("total"));
				dto.setOrderNote(rs.getString("note"));

				list.add(dto);
			}

		} catch (Exception e) {

		}

		return list;
	}
	
	@Override
	public int deleteOrder(SalesDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		try {
			
			sql = "DELETE FROM orderBill WHERE orderNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getOrderNo());
			result = pstmt.executeUpdate();

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
	public int insertRefund(SalesDTO dto, ProductDTO pdto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		
		try {
			sql = "INSERT INTO refund (refundNo, orderNo, refundDate, note) "
					+ " VALUES (refund_no_seq.NEXTVAL, ?, SYSDATE, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getOrderNo());
			pstmt.setString(2, dto.getNote());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			
			sql = "UPDATE product SET stock = stock + ? "
					+ " WHERE productNo = ? ";
			
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, pdto.getProductNo());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<SalesDTO> listRefund() {
		List<SalesDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT refundNo, orderNo, refundDate, note FROM refund";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SalesDTO dto = new SalesDTO();

				dto.setRefundNo(rs.getString("refundNo"));
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setRefundDate(rs.getString("refundDate"));
				dto.setNote(rs.getString("note"));

				list.add(dto);
			}

		} catch (Exception e) {

		}

		return list;
	}
	
	@Override
	public int insertRelease(SalesDTO dto, ProductDTO pdto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		
		try {
			sql = "INSERT INTO release (releaseNo, orderNo, releaseAval, releaseDate) "
					+ " VALUES ((release_no_seq.NEXTVAL), ?, ?, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getOrderNo());
			pstmt.setString(2, dto.getReleaseAval());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			
			sql = "UPDATE product SET stock = stock - ? "
					+ " WHERE productNo = ? ";
			
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, pdto.getProductNo());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<SalesDTO> listRelease() {
		List<SalesDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT releaseNo, orderNo, releaseAval, releaseDate FROM release";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SalesDTO dto = new SalesDTO();

				dto.setReleaseNo(rs.getString("releaseNo"));
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setReleaseAval(rs.getString("releaseAval"));
				dto.setRelDate(rs.getString("releaseDate"));

				list.add(dto);
			}

		} catch (Exception e) {

		}

		return list;
	}

	@Override
	public int insertShipping(SalesDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		
		try {
			sql = "INSERT INTO shipping (shippingNo, releaseNo, shippingState, shDate) "
					+ " VALUES ((shipping_no_seq.NEXTVAL), ?, ?, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getReleaseNo());
			pstmt.setString(2, dto.getShippingState());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<SalesDTO> listShipping() {
		List<SalesDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT shippingNo, releaseNo, shippingState, shDate FROM shipping";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SalesDTO dto = new SalesDTO();

				dto.setShippingNo(rs.getString("shippingNo"));
				dto.setReleaseNo(rs.getString("releaseNo"));
				dto.setShippingState(rs.getString("shippingState"));
				dto.setShDate(rs.getString("shDate"));

				list.add(dto);
			}

		} catch (Exception e) {

		}

		return list;
	}

	@Override
	public int salesInsert(SalesDTO dto, ProductDTO pdto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "INSERT INTO sales (salesNo, stateNo, productNo, customer, sales, salesQty, dealDate)"
					+ " VALUES ('S_'||TO_CHAR(sales_no_seq.NEXTVAL), ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getStateNo());
			pstmt.setString(2, pdto.getProductNo());
			pstmt.setString(3, dto.getCustomer());
			pstmt.setInt(4, pdto.getPrice());
			pstmt.setInt(5, pdto.getStock());
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
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL , ?, ?, '108', ?, ?, '', '미승인', ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(2, accdto.getAccountNo());
			pstmt.setInt(3, accdto.getAmount());
			pstmt.setString(4, accdto.getDetail());
			pstmt.setString(5, accdto.getStateDate());
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			
	
			
		} catch (SQLIntegrityConstraintViolationException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			
			if(e.getErrorCode() == 1) {
				System.out.println("중복 데이터로 등록이 불가능합니다.");	
			}else if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		} catch (SQLDataException e) {
			
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			
			if(e.getErrorCode() == 1840 || e.getErrorCode()==1861) {
				System.out.println("날짜 입력 형식 오류입니다.");
			}else {
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
			if(pstmt != null) {
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
	public int salesAccountInsert2(EmpDTO empdto, AccDTO accdto) throws SQLException {
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
					+ " VALUES (ACCOUNTING_SEQ.NEXTVAL , ?, ?, '120', ?, ?, '', '미승인', ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empdto.getEmpNo());
			pstmt.setString(2, accdto.getAccountNo());
			pstmt.setInt(3, accdto.getAmount());
			pstmt.setString(4, accdto.getDetail());
			pstmt.setString(5, accdto.getStateDate());
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			
	
			
		} catch (SQLIntegrityConstraintViolationException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			
			if(e.getErrorCode() == 1) {
				System.out.println("중복 데이터로 등록이 불가능합니다.");	
			}else if (e.getErrorCode() == 1400) {
				System.out.println("필수 입력 사항을 입력 하지 않았습니다.");
			}else {
				System.out.println(e.toString());
			}
			throw e;
			
		} catch (SQLDataException e) {
			
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			
			if(e.getErrorCode() == 1840 || e.getErrorCode()==1861) {
				System.out.println("날짜 입력 형식 오류입니다.");
			}else {
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
			if(pstmt != null) {
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
					+ " FROM accounting a " + " JOIN accountsub b ON a.accountSubNo = b.accountSubNo "
					+ " JOIN CATEGORY c ON c.categNo = b.categNo "
					+ " WHERE A.accountSubNo = '412' OR A.accountSubNo = '108' OR A.accountSubNo = '120'";

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
	
	@Override
	public SalesDTO listOperatingProfit() {
		SalesDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {					
			sql = "SELECT NVL(sum(amount), 0) AS salesTotal FROM accounting WHERE accountSubNo = '412' ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			dto = new SalesDTO();
			if (rs.next()) {
				dto.setSalesTotal(rs.getInt("salesTotal"));
			}
			rs.close();
			pstmt.close();
			
			sql = "SELECT NVL(sum(amount), 0) AS salesOriginTotal FROM accounting WHERE accountSubNo = '451' OR accountSubNo = '455'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setSalesOriginTotal(rs.getInt("salesOriginTotal"));
			}
			rs.close();
			pstmt.close();
			
			sql = "SELECT NVL(sum(amount), 0) AS othersTotal FROM accounting WHERE accountSubNo = '503' OR accountSubNo = '504'"
					+ "    OR accountSubNo = '505' OR accountSubNo = '510' OR accountSubNo = '511' OR accountSubNo = '512'"
					+ "    OR accountSubNo = '513'";	
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setOthersTotal(rs.getInt("othersTotal"));
			}
			
			sql = "SELECT NVL(sum(amount), 0) AS operatingProfit FROM accounting";	
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setOperatingProfit(rs.getInt("operatingProfit"));
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return dto;
	}

	@Override
	public int taxBillInsert(SalesDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "INSERT INTO taxBill(taxBillNum, salesNo, companyName, name, address, busStatue, currDate, valueSupply, taxAmount,"
					+ "item, num, unitPrice, total, outAmount, note)"
					+ " VALUES(taxBill_no_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getStateNo());
			pstmt.setString(2, dto.getCompanyName());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getBusStatue());
			pstmt.setInt(6, dto.getValueSupply());
			pstmt.setInt(7, dto.getTaxAmount());
			pstmt.setString(8, dto.getItem());
			pstmt.setInt(9, dto.getNum());
			pstmt.setInt(10, dto.getUnitPrice());
			pstmt.setInt(11, dto.getTotal());
			pstmt.setInt(12, dto.getOutAmount());
			pstmt.setString(13, dto.getNote());
			
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
	public List<SalesDTO> listTaxBill() {
		List<SalesDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT taxBillNum, salesNo, companyName, name, address, busStatue, currDate, valueSupply, taxAmount,"
					+ "item, num, unitPrice, total, outAmount, note FROM taxBill";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SalesDTO dto = new SalesDTO();

				dto.setTaxBillNum(rs.getString("taxBillNum"));
				dto.setSalesNo(rs.getString("salesNo"));
				dto.setCompanyName(rs.getString("companyName"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setBusStatue(rs.getString("busStatue"));
				dto.setCurrDate(rs.getString("currDate"));
				dto.setValueSupply(rs.getInt("valueSupply"));
				dto.setTaxAmount(rs.getInt("taxAmount"));
				dto.setItem(rs.getString("item"));			
				dto.setNum(rs.getInt("num"));
				dto.setUnitPrice(rs.getInt("unitPrice"));
				dto.setTotal(rs.getInt("total"));
				dto.setOutAmount(rs.getInt("outAmount"));
				dto.setNote(rs.getString("note"));

				list.add(dto);
			}

		} catch (Exception e) {

		}

		return list;
	}

	@Override
	public List<AccDTO> listMoney() {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT stateNo, c.t_account , accountNo, b.asub_name, amount, cancellation, stateCon, stateDate, empNo "
					+ " FROM accounting a " + " JOIN accountsub b ON a.accountSubNo = b.accountSubNo "
					+ " JOIN CATEGORY c ON c.categNo = b.categNo "
					+ " WHERE A.accountSubNo = '120'";

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

}
