package com.cyj.mystock.service.ztsj;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyj.mystock.dao.ztsj.ZtsjDao;
import com.cyj.mystock.entity.Ztsj;
import com.cyj.mystock.entity.ZtsjAnay;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional(transactionManager="transactionManager")
public class ZtsjService {

	@Autowired
	private ZtsjDao<?> dao;
	
	public ZtsjDao<?> getDao() {
		return dao;
	}	

	public void setDao(ZtsjDao<?> dao) {
		this.dao = dao;
	}

	

	public List<Ztsj> query(Map<String,String> map) {
		return dao.query(map);
	}
	public void saveOrUpdate(Ztsj ztsj){
		this.dao.saveOrUpdate(ztsj);
	}
	public void delete(String id){
		this.dao.delete(id);
	}
	public void deleteBatch(JSONArray ja){
		for(int i=0;i<ja.size();i++){
			Object o = ja.get(i);
			JSONObject jo = JSONObject.fromObject(o);
			this.dao.delete(jo.getString("recid"));
		}
		
	}
	
	public List<Ztsj> queryAnalytics(){
		return dao.queryAnalytics();
	}
	
	public List<Ztsj> query(){
		return dao.query();
	}
	
	public Ztsj queryById(String id){
		return  (Ztsj) dao.queryById(id);
	}
	
	public BigInteger queryCount(Map<String,String> map){
		return dao.queryCount(map);
	}
}
