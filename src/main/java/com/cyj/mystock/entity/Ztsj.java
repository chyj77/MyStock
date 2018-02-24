/**
 * 
 */
package com.cyj.mystock.entity;

import javax.persistence.*;


/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "ZTSJ")
public class Ztsj {

	@Id
	@Column(name="recid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String recid;
	@Column(name = "rq")
	private String rq;
	@Column(name = "mrztgs")
	private String mrztgs;
	@Column(name = "fyzbgs")
	private String fyzbgs;
	@Column(name = "10dqztgs")
	private String dqztgs;
	@Column(name = "ztzdgn")
	private String ztzdgn;
	@Column(name = "ztzdgs")
	private String ztzdgs;
	@Column(name = "dbcrgkl")
	private String dbcrgkl;
	@Column(name = "spcgl")
	private String spcgl;
	@Column(name = "10dqztgkl")
	private String dqztgkl;
	@Column(name = "bzsl")
	private String bzsl;
	@Column(name = "bzl")
	private String bzl;
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
	public String getMrztgs() {
		return mrztgs;
	}
	public void setMrztgs(String mrztgs) {
		this.mrztgs = mrztgs;
	}
	public String getFyzbgs() {
		return fyzbgs;
	}
	public void setFyzbgs(String fyzbgs) {
		this.fyzbgs = fyzbgs;
	}
	public String getDqztgs() {
		return dqztgs;
	}
	public void setDqztgs(String dqztgs) {
		this.dqztgs = dqztgs;
	}
	public String getZtzdgn() {
//		try {
//			if(ztzdgn!=null)
//				ztzdgn=new String(ztzdgn.getBytes(Charset.defaultCharset()),"GB18030");
//		} catch (UnsupportedEncodingException e) {
//			return  null;
//		}
		return ztzdgn;
	}
	public void setZtzdgn(String ztzdgn) {
		this.ztzdgn = ztzdgn;
	}
	public String getZtzdgs() {
		return ztzdgs;
	}
	public void setZtzdgs(String ztzdgs) {
		this.ztzdgs = ztzdgs;
	}
	public String getDbcrgkl() {
		return dbcrgkl;
	}
	public void setDbcrgkl(String dbcrgkl) {
		this.dbcrgkl = dbcrgkl;
	}
	public String getSpcgl() {
		return spcgl;
	}
	public void setSpcgl(String spcgl) {
		this.spcgl = spcgl;
	}
	public String getDqztgkl() {
		return dqztgkl;
	}
	public void setDqztgkl(String dqztgkl) {
		this.dqztgkl = dqztgkl;
	}
	public String getBzsl() {
		return bzsl;
	}
	public void setBzsl(String bzsl) {
		this.bzsl = bzsl;
	}
	public String getBzl() {
		return bzl;
	}
	public void setBzl(String bzl) {
		this.bzl = bzl;
	}


}
