package com.ssfms;

import java.sql.SQLException;


//임시 변경사항있음
import java.util.List;

public interface SalesDAO {
	public int estimateInsertSales(SalesDTO dto) throws SQLException;	
	public int updateSales(SalesDTO dto) throws SQLException;
	public int taxBillInsert(SalesDTO dto) throws SQLException;
	public int salesInsert(SalesDTO dto) throws SQLException;
	public int salesAccountInsert(SalesDTO dto) throws SQLException;
	
	
	public SalesDTO estimateRead(String id);
	public List<SalesDTO> listSales();
	public List<SalesDTO> listSales(String name);
}
