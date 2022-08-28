package com.ssfms;

import java.sql.SQLException;
import java.util.List;

public interface BuyDAO {
	
	//매입처
	public int insertShop (BuyDTO buydto)throws SQLException;
	public int updateShop (BuyDTO buydto)throws SQLException;
	public int deleteShop (BuyDTO buydto)throws SQLException;
	public List<BuyDTO> listShop();
	
	
	//구매
	public int insertBuy (BuyDTO buydto) throws SQLException;
	public int updateBuy (BuyDTO buydto) throws SQLException;
	public int deleteBuy (BuyDTO buydto) throws SQLException;
	public List<BuyDTO> listBuy();
	public List<BuyDTO> partlistAll(String partNo);
	
	
	//전표등록
	public int insertAccBuy (AccDTO accdto, EmpDTO empdto) throws SQLException;
	public int updateAccBuy (AccDTO accdto) throws SQLException; ///전표취소-상태변경
	public List<AccDTO> listAccBuy(String accountSubNo);
	
	
	
}
