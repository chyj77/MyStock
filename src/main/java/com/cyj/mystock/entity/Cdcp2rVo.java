/**
 * 
 */
package com.cyj.mystock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "CDSP2R")
public class Cdcp2rVo {

	@Id
	@Column(name="recid")
	private String recid;
	@Column(name = "rq")
	private String rq;
	@Column(name = "gpdm")
	private String gpdm;
	@Column(name = "gpmc")
	private String gpmc;
	@Column(name = "mrjg")
	private String mrjg;
	@Column(name = "mcjg")
	private String mcjg;
	
	private String ykje;
	
	private String zdl;
	
	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getGpdm() {
		return gpdm;
	}

	public void setGpdm(String gpdm) {
		this.gpdm = gpdm;
	}

	public String getGpmc() {
		return gpmc;
	}

	public void setGpmc(String gpmc) {
		this.gpmc = gpmc;
	}

	public String getMrjg() {
		return mrjg;
	}

	public void setMrjg(String mrjg) {
		this.mrjg = mrjg;
	}

	public String getMcjg() {
		return mcjg;
	}

	public void setMcjg(String mcjg) {
		this.mcjg = mcjg;
	}

	public String getYkje() {
		return ykje;
	}

	public void setYkje(String ykje) {
		this.ykje = ykje;
	}

	public String getZdl() {
		return zdl;
	}

	public void setZdl(String zdl) {
		this.zdl = zdl;
	}


}
