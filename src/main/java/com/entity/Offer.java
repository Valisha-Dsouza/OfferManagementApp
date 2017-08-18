package com.entity;

import java.util.Date;

public class Offer {
	String code;
	Date startTime;
	Date endTime;
	Float discount;
	String discountType;

	public Offer() {
		super();
	}

	public Offer(String code, Date startTime, Date endTime, Float discount, String discountType) {
		super();
		this.code = code;
		this.startTime = startTime;
		this.endTime = endTime;
		this.discount = discount;
		this.discountType = discountType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

}
