package com.dss.testapp;

public class ProductItem {
	private int productId;
	private String productName;
	private float price;
	private String colorCode;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public ProductItem(int productId, String productName, float price, String color) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.colorCode = color;
	}

}
