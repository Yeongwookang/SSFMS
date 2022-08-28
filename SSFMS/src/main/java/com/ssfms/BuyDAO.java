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
	public int insertAccBuy (BuyDTO buydto) throws SQLException;
	
	
}
