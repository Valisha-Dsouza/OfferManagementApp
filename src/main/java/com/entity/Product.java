package com.entity;

import java.util.*;

public class Product {
	String code;
	String name;
	int stock;
	Date createdOn;

	public Product() {
		super();
	}

	public Product(String code, String name, int stock, Date createdOn) {
		super();
		this.code = code;
		this.name = name;
		this.stock = stock;
		this.createdOn = createdOn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
