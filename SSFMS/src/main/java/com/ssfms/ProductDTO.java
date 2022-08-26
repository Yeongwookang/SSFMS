package com.ssfms;

public class ProductDTO {
private String productNo;
private String product_name;
private int cost;
private int price;
private int stock;

public String getProductNo() {
	return productNo;
}
public void setProductNo(String productNo) {
	this.productNo = productNo;
}
public String getProduct_name() {
	return product_name;
}
public void setProduct_name(String product_name) {
	this.product_name = product_name;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
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

}