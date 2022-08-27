package com.ssfms;

import java.sql.SQLException;
import java.util.List;


public interface EmpDAO {
	// 사원리스트
	public int insertEmp(EmpDTO dto) throws SQLException;
	public int updateEmp(EmpDTO dto) throws SQLException;
	public List<EmpDTO> listEmp();
	// 경력사항
	public int insertCare(EmpDTO dto) throws SQLException;
	public int updateCare(EmpDTO dto) throws SQLException;
	public List<EmpDTO> listCare();
	// 연봉
	public int insertAsal(EmpDTO adto) throws SQLException;
	public int updateAsal(EmpDTO adto) throws SQLException;
	public List<EmpDTO> listAsal();
	// 급여관리
	public int insertSett(EmpDTO dto) throws SQLException;
	public int updateSett(EmpDTO dto) throws SQLException;
	public List<EmpDTO> listSett();
	// 근태관리
	public int insertAtt(EmpDTO dto) throws SQLException;
	public int updateAtt(EmpDTO dto) throws SQLException;
	public List<EmpDTO> listAtt();
	
	
	
}
