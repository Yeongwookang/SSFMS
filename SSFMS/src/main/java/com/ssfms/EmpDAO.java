package com.ssfms;

import java.sql.SQLException;
import java.util.List;


public interface EmpDAO {
	public int insertEmp(EmpDTO dto) throws SQLException;
	public int insertAsal(EmpDTO adto) throws SQLException;
	public List<EmpDTO> listEmp();
}
