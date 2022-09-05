package com.ssfms;

import java.sql.SQLException;


//임시 변경사항있음
import java.util.List;

public interface SalesDAO {
	public void estimateInsertSales(List<SalesDTO> list) throws SQLException;
	public int taxBillInsert(SalesDTO dto) throws SQLException;
	public int salesInsert(SalesDTO dto, ProductDTO pdto) throws SQLException;
	public int salesAccountInsert(EmpDTO empdto, AccDTO accdto) throws SQLException;
	public int salesAccountInsert2(EmpDTO empdto, AccDTO accdto) throws SQLException;
	public int deleteOrder(SalesDTO dto) throws SQLException;
	public int insertRelease(SalesDTO dto, ProductDTO pdto) throws SQLException;
	public int insertShipping(SalesDTO dto) throws SQLException;
	public int insertRefund (SalesDTO dto, ProductDTO pdto) throws SQLException;
	
	
	public List<SalesDTO> estimateRead();
	public List<SalesDTO> listSales();
	public List<AccDTO> listSalesAccountInsert();
	public List<SalesDTO> listTaxBill();
	public List<AccDTO> listMoney();
	public List<SalesDTO> orderRead();
	public List<SalesDTO> listRelease();
	public List<SalesDTO> listShipping();
	public List<SalesDTO> listRefund();
	public SalesDTO listOperatingProfit() throws SQLException;
	}
