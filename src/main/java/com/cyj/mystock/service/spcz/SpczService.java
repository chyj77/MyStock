package com.cyj.mystock.service.spcz;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cyj.mystock.entity.CcgpVO;
import com.cyj.mystock.service.ccgp.CcgpService;
import com.cyj.mystock.thread.QueryStockThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyj.mystock.dao.spcz.SpczDao;
import com.cyj.mystock.entity.Spcz;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		reload();
	}
	private void reload(){
		QueryStockThread thread = QueryStockThread.getInstance();
		QueryStockThread.IsBreak=false;
		ccgpService.init();
		SimpleDateFormat format = new SimpleDateFormat("HHmm");
		Date date = new Date();
		String nowDateValue = format.format(date);
		int now = Integer.parseInt(nowDateValue);
		System.out.println("---------now---------"+now);
		if(now<1510 && now>=915) {
            QueryStockThread.IsBreak=true;
			new Thread(thread).start();
		}
	}
	public void delete(String id){
		this.dao.delete(id);
		reload();
	}
	public void deleteBatch(JSONArray ja){
		for(int i=0;i<ja.size();i++){
			Object o = ja.get(i);
			JSONObject jo = JSONObject.fromObject(o);
			this.dao.delete(jo.getString("recid"));
		}
		reload();
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
