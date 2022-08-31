package com.ssfms;

import java.sql.SQLException;
import java.util.List;


public interface AccDAO {
	public int insertAccount(AccDTO dto) throws SQLException;
	public int updateAccount(AccDTO dto) throws SQLException;
	public int deleteAccount(int stateNo) throws SQLException;
	
	public AccDTO readAccount(int stateNo)throws SQLException;
	public List<AccDTO> listAccount()throws SQLException;
	public List<AccDTO> listAccount_emp(String empNo)throws SQLException;
	public List<AccDTO> listAccount_subNo(String accountSubNo)throws SQLException;
	public List<AccDTO> listNapproval()throws SQLException;
	public List<AccDTO> listapproval()throws SQLException;
} 

