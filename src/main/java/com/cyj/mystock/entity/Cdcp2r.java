/**
 * 
 */
package com.cyj.mystock.entity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "CDSP2R")
public class Cdcp2r {

	@Id
	@Column(name="recid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String recid;
	@Column(name = "rq")
	private String rq;
	@Column(name = "gpdm", length = 6)
	private String gpdm;
	@Column(name = "gpmc", length = 255)
	private String gpmc;
	@Column(name = "mrjg")
	private String mrjg;
	@Column(name = "mcjg")
	private String mcjg;
	
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
		try {
			gpmc=new String(gpmc.getBytes(Charset.defaultCharset()),"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


}
