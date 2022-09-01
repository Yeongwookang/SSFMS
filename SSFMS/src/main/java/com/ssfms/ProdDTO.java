package com.ssfms;

import java.sql.Date;

public class ProdDTO {
private int prodNo;
private int stateNo;
private String productNo;
private String product_name;


private int qty;
private int cost;
private int price;

private int stock;
private Date prod_Date;
private String partNo;
private String part_name;
private int part_price;
private int part_stock;
private String empNo;
private int partOfferNo;
private String offer_date;


public String getProduct_name() {
	return product_name;
}

public void setProduct_name(String product_name) {
	this.product_name = product_name;
}
public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public int getStock() {
	return stock;
}

public void setStock(int stock) {
	this.stock = stock;
}

public String getOffer_date() {
	return offer_date;
}

public void setOffer_date(String offer_date) {
	this.offer_date = offer_date;
}

public int getPartOfferNo() {
	return partOfferNo;
}

public void setPartOfferNo(int partOfferNo) {
	this.partOfferNo = partOfferNo;
}

public String getEmpNo() {
	return empNo;
}

public void setEmpNo(String empNo) {
	this.empNo = empNo;
}

public String getPart_name() {
	return part_name;
}

public void setPart_name(String part_name) {
	this.part_name = part_name;
}

public int getPart_price() {
	return part_price;
}

public void setPart_price(int part_price) {
	this.part_price = part_price;
}

public int getPart_stock() {
	return part_stock;
}

public void setPart_stock(int part_stock) {
	this.part_stock = part_stock;
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

public int getProdNo() {
	return prodNo;
}

public void setProdNo(int prodNo) {
	this.prodNo = prodNo;
}


	
	
}
