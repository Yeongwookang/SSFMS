package com.ssfms;


//임시 변경사항있음
public class SalesDTO {
	private String salesNo;
	private String stateNo;
	private String productCode;
	private String customer;
	private String sales;
	private String salesQty;
	
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
	public String getStateNo() {
		return stateNo;
	}
	public void setStateNo(String stateNo) {
		this.stateNo = stateNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	public String getDeal_Date() {
		return deal_Date;
	}
	public void setDeal_Date(String deal_Date) {
		this.deal_Date = deal_Date;
	}
	private String deal_Date;
	
	
	
}
