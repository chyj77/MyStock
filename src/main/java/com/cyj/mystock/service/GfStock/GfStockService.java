package com.cyj.mystock.service.GfStock;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyj.mystock.dao.gfStock.GfStockDao;
import com.cyj.mystock.entity.GfStock;

@Service
@Transactional(transactionManager="transactionManager")
public class GfStockService {

	@Autowired
	private GfStockDao<?> dao;
	
	public GfStockDao<?> getDao() {
		return dao;
	}	

	public void setDao(GfStockDao<?> dao) {
		this.dao = dao;
	}


	public List<GfStock> query(Map<String,String> map) {
		return dao.query(map);
	}
	public BigInteger queryCount(Map<String,String> map){
		return dao.queryCount(map);
	}
	public Map<String,Object> queryGfStock(Map<String,String> map) {
		return dao.queryGfStock(map);
	}
}
