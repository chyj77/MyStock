package com.cyj.mystock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


@Entity
@Table(name = "CCGP")
public class Ccgp {


	@Column(name = "rq")
	private String rq;
	@Id
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "buyprice")
	private String buyprice;
	@Column(name = "sl")
	private String sl;

	private String nowprice;

	private Double jiacha;

	private Double zdl;
	@Column(name = "ccday")
	private String ccday;

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

	public String getNowprice() {
		return nowprice;
	}

	public void setNowprice(String nowprice) {
		this.nowprice = nowprice;
	}

	public Double getJiacha() {
		return jiacha;
	}

	public void setJiacha(Double jiacha) {
		this.jiacha = jiacha;
	}

	public Double getZdl() {
		return zdl;
	}

	public void setZdl(Double zdl) {
		this.zdl = zdl;
	}

	public String getCcday() {
		return ccday;
	}

	public void setCcday(String ccday) {
		this.ccday = ccday;
	}
	
	
	
}
