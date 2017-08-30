package com.cyj.mystock.dao.ztsj;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cyj.mystock.dao.HibernateDao;
import com.cyj.mystock.entity.Ztsj;

@Repository
public class ZtsjDao<T> extends HibernateDao<T> {

	/**
	 * 查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ztsj> query(Map<String,String> map) {
		String queryString = "SELECT ztsj.rq AS rq,ztsj.mrztgs AS mrztgs,ztsj.fyzbgs AS fyzbgs,ztsj.10dqztgs AS 10dqztgs,"
							+"ztsj.ztzdgn AS ztzdgn,ztsj.ztzdgs AS ztzdgs,"
							+ "ROUND(ztsj.dbcrgkl*100,2) AS dbcrgkl,"
							+ "ROUND(ztsj.spcgl*100,2) AS spcgl,ztsj.recid as recid,"
							+ "ROUND(ztsj.10dqztgkl*100,2) AS 10dqztgkl,ztsj.bzsl AS bzsl,"
							+ "ROUND(ztsj.bzl*100,2) AS bzl"
							+ " FROM ztsj ";
		if(map!=null&&map.size()>0){
			String page = map.get("page");
			String rp = map.get("rp");
			String sortname = map.get("sortname");
			String sortorder = map.get("sortorder");
			String val = map.get("query");
			String qtype = map.get("qtype");
			String cond = " where 1=1 ";
			String limit="";
			if(page!=null && !"".equals(page)){
				if("1".equals(page)){
					limit =" LIMIT "+ (Integer.parseInt(page)-1) +" , "+rp;
				}else{
					limit =" LIMIT "+  (Integer.parseInt(page)-1)*Integer.parseInt(rp)+" , "
							+rp;
				}
			}
			if(map.get("query")!=null && !"".equals(map.get("query"))){
				cond+=" and "+qtype+"='"+val+"'";
			}
			String sort =" order by "+sortname+" " +sortorder;
			queryString +=cond + sort + limit;
		}
		return (List<Ztsj>) this.queryAll(queryString, Ztsj.class); 
	}
	public List<Ztsj> query(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("sortorder", "asc");
		map.put("sortname", "ztzdgn");
		return this.query(map);
	}
	public void saveOrUpdate(Ztsj ztsj){
		super.saveOrUpdate(ztsj);
	}
	public void delete(String id){
		super.delete(id,Ztsj.class);
	}
	public Ztsj queryById(String id){
		return (Ztsj) super.queryById(id, Ztsj.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ztsj> queryAnalytics() {
		String queryString = "SELECT count(ztsj.rq) AS rq,ROUND(sum(ztsj.mrztgs)/count(*),2) AS mrztgs,"
				+ "ROUND(sum(ztsj.fyzbgs)/count(*),2) AS fyzbgs,"
				+ "ROUND(sum(ztsj.10dqztgs)/count(*),2) AS 10dqztgs,"
				+"'' AS ztzdgn,ROUND(sum(ztsj.ztzdgs)/count(*),2) AS ztzdgs,"
				+ "ROUND(sum(ztsj.dbcrgkl)/count(*)*100,2) AS dbcrgkl,"
				+ "ROUND(sum(ztsj.spcgl)/count(*)*100,2) AS spcgl,'' as recid,"
				+ "ROUND(sum(ztsj.10dqztgkl)/count(*)*100,2) AS 10dqztgkl,"
				+ "ROUND(sum(ztsj.bzsl)/count(*),2) AS bzsl,"
				+ "ROUND(sum(ztsj.bzl)/count(*)*100,2) AS bzl"
				+ " FROM ztsj ";
		return (List<Ztsj>) super.queryAll(queryString, Ztsj.class);
	}
	
	public BigInteger queryCount(Map<String,String> map){
		String queryString = "SELECT count(*) "
				+ " FROM ztsj ";
		if(map.get("query")==null || "".equals(map.get("query"))){
			
		}else{
			String val = map.get("query");
			String qtype = map.get("qtype");
			
			queryString += " where "+qtype+"='"+val+"'";
		}
		return super.queryCount(queryString);
	}
}
