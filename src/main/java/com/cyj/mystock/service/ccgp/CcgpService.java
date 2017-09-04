package com.cyj.mystock.service.ccgp;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.mystock.dao.ccgp.CcgpDao;
import com.cyj.mystock.entity.Ccgp;

@Service
public class CcgpService {

	@Autowired
	private CcgpDao<?> dao;
	
	public List<Ccgp> query(Map<String,String> map) {
		return dao.query(map);
	}
	
	public BigInteger queryCount(Map<String,String> map){
		return dao.queryCount(map);
	}
	
	public List<Ccgp> query(){
		return dao.query();
	}
}
