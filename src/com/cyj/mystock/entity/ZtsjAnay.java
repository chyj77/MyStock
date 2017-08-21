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
@Table(name = "V_ZTSJ_FX")
public class ZtsjAnay {

	
	@Column(name = "ztzs")
	private String ztzs;
	@Column(name = "fyzbzs")
	private String fyzbzs;
	@Column(name = "10dqztzs")
	private String dqztzs;
	@Id
	@Column(name = "ztzdgn")
	private String ztzdgn;
	@Column(name = "ztzdzs")
	private String ztzdzs;
	@Column(name = "dbcrgkpjl")
	private String dbcrgkpjl;
	@Column(name = "spcgpjl")
	private String spcgpjl;
	@Column(name = "10qztgkpjl")
	private String qztgkpjl;
	@Column(name = "bzzs")
	private String bzzs;
	@Column(name = "bzpjl")
	private String bzpjl;
	public String getZtzs() {
		return ztzs;
	}
	public void setZtzs(String ztzs) {
		this.ztzs = ztzs;
	}
	public String getFyzbzs() {
		return fyzbzs;
	}
	public void setFyzbzs(String fyzbzs) {
		this.fyzbzs = fyzbzs;
	}
	public String getDqztzs() {
		return dqztzs;
	}
	public void setDqztzs(String dqztzs) {
		this.dqztzs = dqztzs;
	}
	public String getZtzdgn() {
		return ztzdgn;
	}
	public void setZtzdgn(String ztzdgn) {
		this.ztzdgn = ztzdgn;
	}
	public String getZtzdzs() {
		return ztzdzs;
	}
	public void setZtzdzs(String ztzdzs) {
		this.ztzdzs = ztzdzs;
	}
	public String getDbcrgkpjl() {
		return dbcrgkpjl;
	}
	public void setDbcrgkpjl(String dbcrgkpjl) {
		this.dbcrgkpjl = dbcrgkpjl;
	}
	public String getSpcgpjl() {
		return spcgpjl;
	}
	public void setSpcgpjl(String spcgpjl) {
		this.spcgpjl = spcgpjl;
	}
	public String getQztgkpjl() {
		return qztgkpjl;
	}
	public void setQztgkpjl(String qztgkpjl) {
		this.qztgkpjl = qztgkpjl;
	}
	public String getBzzs() {
		return bzzs;
	}
	public void setBzzs(String bzzs) {
		this.bzzs = bzzs;
	}
	public String getBzpjl() {
		return bzpjl;
	}
	public void setBzpjl(String bzpjl) {
		this.bzpjl = bzpjl;
	}


}
