package com.ssfms;


//임시 변경사항있음
public class SalesDTO {
	//매출
	private String salesNo;
	private int stateNo;
	private String productNo;
	private String customer;
	private int sales;
	private int salesQty;
	private String dealDate;
	
	//세금계산서
	private String taxBillNum;
	private String companyName;
	private String name;
	private String address;
	private String busStatue;
	private int valueSupply;
	private int taxAmount;
	private String item;
    private int num;
    private String currDate;
	private int unitPrice;
	private int total;
    private int outAmount;
    private String note;
	
	
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
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
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(int salesQty) {
		this.salesQty = salesQty;
	}
	public String getTaxBillNum() {
		return taxBillNum;
	}
	public void setTaxBillNum(String taxBillNum) {
		this.taxBillNum = taxBillNum;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusStatue() {
		return busStatue;
	}
	public void setBusStatue(String busStatue) {
		this.busStatue = busStatue;
	}
	public int getValueSupply() {
		return valueSupply;
	}
	public void setValueSupply(int valueSupply) {
		this.valueSupply = valueSupply;
	}
	public int getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getTotal() {
		return num*unitPrice;
	}
	public int getOutAmount() {
		return outAmount;
	}
	public void setOutAmount(int outAmount) {
		this.outAmount = outAmount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDealDate() {
		return dealDate;
	}
	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}	
    public String getCurrDate() {
		return currDate;
	}
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
