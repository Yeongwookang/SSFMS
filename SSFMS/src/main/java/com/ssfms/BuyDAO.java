package com.ssfms;

import java.sql.SQLException;
import java.util.List;

public interface BuyDAO {
	
	public int insertShop (BuyDTO buydto)throws SQLException;
	public int updateShop (BuyDTO buydto)throws SQLException;
	public int deleteShop (BuyDTO buydto)throws SQLException;
	public List<BuyDTO> listShop();
	
	
	
	public int insertBuy (BuyDTO buydto) throws SQLException;
	public int updateBuy (BuyDTO buydto) throws SQLException;
	

	
}
