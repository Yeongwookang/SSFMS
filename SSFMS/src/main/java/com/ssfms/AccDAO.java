package com.ssfms;

import java.sql.SQLException;
import java.util.List;


public interface AccDAO {
	// 전표관련
	public int insertAccount(AccDTO dto) throws SQLException;
	public int updateAccount(AccDTO dto) throws SQLException;
	public int deleteAccount(int stateNo) throws SQLException;
	
	public AccDTO readAccount(int stateNo)throws SQLException;
	public List<AccDTO> listAccount()throws SQLException;
	public List<AccDTO> listAccount_emp(String empNo)throws SQLException;
	public List<AccDTO> listAccount_subNo(String accountSubNo)throws SQLException;
	public List<AccDTO> listNapproval()throws SQLException;
	public List<AccDTO> listapproval()throws SQLException;

	// 계좌관련
	public int insertAccountNo(AccDTO adto) throws SQLException;
	public int updateAccountNo(AccDTO adto) throws SQLException;
	public int deleteAccountNo(int setAccountNo) throws SQLException;
	
	public AccDTO readAccountNO(int setAccountNo)throws SQLException;
	public List<AccDTO> listAccountNo()throws SQLException;
	
	// 계정과목
	public int insertAccSub(AccDTO sdto) throws SQLException;
	public int updateAccSub(AccDTO sdto) throws SQLException;
	public int deleteAccSub(int accountSubNo) throws SQLException;
	
	public AccDTO readAccSub(int accountSubNo)throws SQLException;
	public List<AccDTO> listAccSub()throws SQLException;
} 


