package com.cyj.mystock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MYSTOCK")
public class GfStock {

	@Id
	@Column(name="recid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String recid;
	@Column(name = "stockdate")
	private String stockdate;
	@Column(name = "stockcode")
	private String stockcode;
	@Column(name = "stockname")
	private String stockname;
	@Column(name = "oper")
	private String oper;
	@Column(name = "stocknum")
	private String stocknum;
	@Column(name = "stockprice")
	private String stockprice;
	@Column(name = "stockallprice")
	private String stockallprice;
	@Column(name = "stocksl")
	private String stocksl;
	@Column(name = "stockkysl")
	private String stockkysl;
	@Column(name = "fsje")
	private String fsje;
	@Column(name = "yhs")
	private String yhs;
	@Column(name = "htbh")
	private String htbh;
	@Column(name = "kyje")
	private String kyje;
	@Column(name = "gdzh")
	private String gdzh;
	@Column(name = "ghf")
	private String ghf;
	@Column(name = "jsf")
	private String jsf;
	@Column(name = "zgf")
	private String zgf;
	@Column(name = "yj")
	private String yj;
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	public String getStockdate() {
		return stockdate;
	}
	public void setStockdate(String stockdate) {
		this.stockdate = stockdate;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getStocknum() {
		return stocknum;
	}
	public void setStocknum(String stocknum) {
		this.stocknum = stocknum;
	}
	public String getStockprice() {
		return stockprice;
	}
	public void setStockprice(String stockprice) {
		this.stockprice = stockprice;
	}
	public String getStockallprice() {
		return stockallprice;
	}
	public void setStockallprice(String stockallprice) {
		this.stockallprice = stockallprice;
	}
	public String getStocksl() {
		return stocksl;
	}
	public void setStocksl(String stocksl) {
		this.stocksl = stocksl;
	}
	public String getStockkysl() {
		return stockkysl;
	}
	public void setStockkysl(String stockkysl) {
		this.stockkysl = stockkysl;
	}
	public String getFsje() {
		return fsje;
	}
	public void setFsje(String fsje) {
		this.fsje = fsje;
	}
	public String getYhs() {
		return yhs;
	}
	public void setYhs(String yhs) {
		this.yhs = yhs;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getKyje() {
		return kyje;
	}
	public void setKyje(String kyje) {
		this.kyje = kyje;
	}
	public String getGdzh() {
		return gdzh;
	}
	public void setGdzh(String gdzh) {
		this.gdzh = gdzh;
	}
	public String getGhf() {
		return ghf;
	}
	public void setGhf(String ghf) {
		this.ghf = ghf;
	}
	public String getJsf() {
		return jsf;
	}
	public void setJsf(String jsf) {
		this.jsf = jsf;
	}
	public String getZgf() {
		return zgf;
	}
	public void setZgf(String zgf) {
		this.zgf = zgf;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
}
