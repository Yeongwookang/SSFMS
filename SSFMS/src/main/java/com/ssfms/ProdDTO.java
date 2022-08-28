package com.ssfms;

import java.sql.Date;
import java.sql.SQLException;

public class ProdDTO {
private String prodNo;
private int stateNo;
private String productNo;
private int qty;
private int cost;
private Date prod_Date;
private String partNo;
private int part_stock;
public int partStock(String partNo) throws SQLException {
	// TODO Auto-generated method stub
	return 0;
}





public int getStateNo() {
	return stateNo;
}


public void setStateNo(int stateNo) {
	this.stateNo = stateNo;
}


public String getProductNo() {
	return productNo;
}
public void setProductNo(String productNo) {
	this.productNo = productNo;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public Date getProd_Date() {
	return prod_Date;
}
public void setProd_Date(Date prod_Date) {
	this.prod_Date = prod_Date;
}
public String getPartNo() {
	return partNo;
}
public void setPartNo(String partNo) {
	this.partNo = partNo;
}
public int getPart_stock() {
	return part_stock;
}
public void setPart_stock(int part_stock) {
	this.part_stock = part_stock;
}





public String getProdNo() {
	return prodNo;
}





public void setProdNo(String prodNo) {
	this.prodNo = prodNo;
}
	
	
}
