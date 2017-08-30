package com.cyj.mystock.entity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHIPANCAOZUO")
public class Spcz {

	@Id
	@Column(name="recid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String recid;
	@Column(name = "rq")
	private String rq;
	@Column(name = "code", length = 6)
	private String code;
	@Column(name = "name", length = 255)
	private String name;
	@Column(name = "caozuo")
	private String caozuo;
	@Column(name = "jiage")
	private String jiage;
	@Column(name = "sl")
	private String sl;
	@Column(name = "luoji")
	private String luoji;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		try {
			if(name!=null)
				name=new String(name.getBytes(Charset.defaultCharset()),"GBK");
		} catch (UnsupportedEncodingException e) {
			return  null;
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaozuo() {
		return caozuo;
	}
	public void setCaozuo(String caozuo) {
		this.caozuo = caozuo;
	}
	public String getJiage() {
		return jiage;
	}
	public void setJiage(String jiage) {
		this.jiage = jiage;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getLuoji() {
		return luoji;
	}
	public void setLuoji(String luoji) {
		this.luoji = luoji;
	}
	
}
