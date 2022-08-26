package com.ssfms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.util.DBConn;



//임시 변경사항있음

public class SalesDAOImpl implements SalesDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertSales(SalesDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSales(SalesDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSales(String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SalesDTO readSales(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesDTO> listSales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesDTO> listSales(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

