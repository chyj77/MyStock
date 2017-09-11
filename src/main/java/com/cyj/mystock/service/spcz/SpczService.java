package com.cyj.mystock.service.spcz;

import com.cyj.mystock.dao.spcz.SpczDao;
import com.cyj.mystock.entity.Spcz;
import com.cyj.mystock.service.ccgp.CcgpService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@Transactional(transactionManager="transactionManager")
public class SpczService {

	@Autowired
	private SpczDao<?> dao;
	@Autowired
	CcgpService ccgpService;
	
	public SpczDao<?> getDao() {
		return dao;
	}	

	public void setDao(SpczDao<?> dao) {
		this.dao = dao;
	}

	

	public List<Spcz> query(Map<String,String> map) {
		return dao.query(map);
	}
	public void saveOrUpdate(Spcz spcz){
		this.dao.saveOrUpdate(spcz);
		ccgpService.reload();
	}

	public void delete(String id){
		this.dao.delete(id);
		ccgpService.reload();
	}
	public void deleteBatch(JSONArray ja){
		for(int i=0;i<ja.size();i++){
			Object o = ja.get(i);
			JSONObject jo = JSONObject.fromObject(o);
			this.dao.delete(jo.getString("recid"));
		}
		ccgpService.reload();
	}
	
	public Spcz queryById(String id){
		return  (Spcz) dao.queryById(id);
	}
	
	public BigInteger queryCount(Map<String,String> map){
		return dao.queryCount(map);
	}
	public List<Map<String,Object>> queryStat(){
		return dao.queryStat();
	}
	public List<Spcz> queryDetail(String code){
		return dao.queryDetail(code);
	}

}
