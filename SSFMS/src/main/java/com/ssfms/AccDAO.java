package com.ssfms;

import java.sql.SQLException;
import java.util.List;


public interface AccDAO {
	public int insertAccount(AccDTO dto) throws SQLException;
	public int updateAccount(AccDTO dto) throws SQLException;
	public int deleteAccount(int stateNo) throws SQLException;
	
	public AccDTO readAccount(int stateNo);
	public List<AccDTO> listAccount();
	public List<AccDTO> listAccount_emp(String empNo);
	public List<AccDTO> listAccount_subNo(String accountSubNo);
} 

