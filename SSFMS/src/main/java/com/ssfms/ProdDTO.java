package com.ssfms;

import java.sql.Date;

public class ProdDTO {
private String prodNo;
private String stateNo;
private String productNo;
private int qty;
private int cost;
private Date prod_Date;
private String partNo;

public String getProdNo() {
	return prodNo;
}
public void setProdNo(String prodNo) {
	this.prodNo = prodNo;
}
public String getStateNo() {
	return stateNo;
}
public void setStateNo(String stateNo) {
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
	
	
}
