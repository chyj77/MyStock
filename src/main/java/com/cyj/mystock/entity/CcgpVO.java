package com.cyj.mystock.entity;

import java.io.Serializable;


public class CcgpVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String rq;
	private String code;
	private String name;
	private String buyprice;
	private String sl;
	private String ccday;
	private String nowprice;
	private String yke;
	private String zdl;

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
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

	public String getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(String buyprice) {
		this.buyprice = buyprice;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getCcday() {
		return ccday;
	}

	public void setCcday(String ccday) {
		this.ccday = ccday;
	}

	public String getNowprice() {
		return nowprice;
	}

	public void setNowprice(String nowprice) {
		this.nowprice = nowprice;
	}

	public String getYke() {
		return yke;
	}

	public void setYke(String yke) {
		this.yke = yke;
	}

	public String getZdl() {
		return zdl;
	}

	public void setZdl(String zdl) {
		this.zdl = zdl;
	}
}
