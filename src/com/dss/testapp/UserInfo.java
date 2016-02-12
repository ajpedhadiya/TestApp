package com.dss.testapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

public class UserInfo {
	private String fname;
	private String lname;
	private String cardNo;
	private String cvv;
	private String exDate;
	private String exMonth;
	private String exYear;
	private String add1;
	private String add2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String comment;

	public UserInfo(String fname, String lname, String cardNo, String cvv, String exDate, String exMonth, String exYear,
			String add1, String add2, String city, String state, String zipcode, String country, String comment) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.exDate = exDate;
		this.exMonth = exMonth;
		this.exYear = exYear;
		this.add1 = add1;
		this.add2 = add2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.comment = comment;
	}

	public UserInfo(String json) {
		try {
			JSONObject jobject = new JSONObject(json);
			setFname(jobject.optString("fname"));
			setLname(jobject.optString("lname"));
			setCardNo(jobject.optString("cardNo"));
			setCvv(jobject.optString("cvv"));
			setExDate(jobject.optString("exDate"));
			setExMonth(jobject.optString("exMonth"));
			setExYear(jobject.optString("exYear"));
			setAdd1(jobject.optString("add1"));
			setAdd2(jobject.optString("add2"));
			setCity(jobject.optString("city"));
			setState(jobject.optString("state"));
			setZipcode(jobject.optString("zipcode"));
			setCountry(jobject.optString("country"));
			setComment(jobject.optString("comment"));

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExDate() {
		return exDate;
	}

	public void setExDate(String exDate) {
		this.exDate = exDate;
	}

	public String getExMonth() {
		return exMonth;
	}

	public void setExMonth(String exMonth) {
		this.exMonth = exMonth;
	}

	public String getExYear() {
		return exYear;
	}

	public void setExYear(String exYear) {
		this.exYear = exYear;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJsonResponse() {
		JSONObject object = new JSONObject();
		try {

			object.put("fname", getFname());
			object.put("lname", getLname());
			object.put("cardNo", getCardNo());
			object.put("cvv", getCvv());
			object.put("exDate", getExDate());
			object.put("exMonth", getExMonth());
			object.put("exYear", getExYear());
			object.put("add1", getAdd1());
			object.put("add2", getAdd2());
			object.put("city", getCity());
			object.put("state", getState());
			object.put("zipcode", getZipcode());
			object.put("country", getCountry());
			object.put("comment", getComment());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return object.toString();
		}
	}

}
