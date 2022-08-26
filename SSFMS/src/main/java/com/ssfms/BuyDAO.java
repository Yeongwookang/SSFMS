package com.ssfms;

import java.sql.SQLException;

public interface BuyDAO {
	
	public int insertShop (BuyDTO buydto)throws SQLException;
	public int updateShop (BuyDTO buydto)throws SQLException;
	public int deleteShop (BuyDTO buydto)throws SQLException;
	
	
	
}
