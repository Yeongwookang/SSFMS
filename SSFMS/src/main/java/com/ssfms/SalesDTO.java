package com.ssfms;

public class SalesDTO {
	//견적서
	private String estimateNo;
	//private String companyName;
	private String comRegiNo;
	private String tel;
	private String orderCom;
	//private String name;
	private String orderComTel;
	private String eDate;
	//private String productNo;
	private String productName; 
	//private int num; 
	private int eCos;
	private int ePrice;
	//private int note;
	
	//주문서
	private String orderNo;
	private String oDate;
	//private String orderCom;
	private String oName;
	private String oTel;
	private String expDeliDate;
	//private String companyName;
	//private String comRegiNo;
	private String comAddress;
	private String comTel;
	//private String productNo;
	//private String productName;
	private int orderNum;
	private int oCost;
	private int oPrice;
	private int oTotal;
	private String orderNote;
	
	
	//출고
	private String releaseAval;
	private String relDate;
	
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
	
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	public String getComRegiNo() {
		return comRegiNo;
	}
	public void setComRegiNo(String comRegiNo) {
		this.comRegiNo = comRegiNo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOrderCom() {
		return orderCom;
	}
	public void setOrderCom(String orderCom) {
		this.orderCom = orderCom;
	}
	public String getOrderComTel() {
		return orderComTel;
	}
	public void setOrderComTel(String orderComTel) {
		this.orderComTel = orderComTel;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int geteCos() {
		return eCos;
	}
	public void seteCos(int eCos) {
		this.eCos = eCos;
	}
	public int getePrice() {
		return ePrice;
	}
	public void setePrice(int ePrice) {
		this.ePrice = ePrice;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getoDate() {
		return oDate;
	}
	public void setoDate(String oDate) {
		this.oDate = oDate;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoTel() {
		return oTel;
	}
	public void setoTel(String oTel) {
		this.oTel = oTel;
	}
	public String getExpDeliDate() {
		return expDeliDate;
	}
	public void setExpDeliDate(String expDeliDate) {
		this.expDeliDate = expDeliDate;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getoCost() {
		return oCost;
	}
	public void setoCost(int oCost) {
		this.oCost = oCost;
	}
	public int getoPrice() {
		return oPrice;
	}
	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}
	public int getoTotal() {
		return num*oPrice;
	}
	public void setoTotal(int oTotal) {
		this.oTotal = oTotal;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	public String getReleaseAval() {
		return "출고등록완료";
	}
	public void setReleaseAval(String releaseAval) {
		this.releaseAval = releaseAval;
	}
	public String getRelDate() {
		return relDate;
	}
	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}
	
}
