package com.ssfms;

import java.sql.SQLException;

public interface ProdDAO {
	public AccDTO prodstateView (AccDTO state) throws SQLException;
	public void reg_product (ProductDTO pdto) throws SQLException;
	public void del_product (String prodNo) throws SQLException;
	public void using_part(BuyDTO bdto ) throws SQLException;
	public ProdDTO producing (ProdDTO pdto) throws SQLException;
	public ProductDTO productStock (String product_name) throws SQLException;
}
