package com.ssfms;

import java.sql.SQLException;


//임시 변경사항있음
import java.util.List;

public interface SalesDAO {
	public int estimateInsertSales(SalesDTO dto) throws SQLException;
	public int taxBillInsert(SalesDTO dto) throws SQLException;
	public int salesInsert(SalesDTO dto, ProductDTO pdto) throws SQLException;
	public int salesAccountInsert(EmpDTO empdto, AccDTO accdto) throws SQLException;
	public int deleteOrder(SalesDTO dto) throws SQLException;
	public int insertRelease(SalesDTO dto) throws SQLException;
	
	
	public List<SalesDTO> estimateRead();
	public List<SalesDTO> listSales();
	public List<AccDTO> listSalesAccountInsert();
	public List<SalesDTO> listTaxBill();
	public List<AccDTO> listMoney();
	public List<SalesDTO> orderRead();
	}
