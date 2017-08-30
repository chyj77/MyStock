package com.cyj.mystock.dao.cdcp2r;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyj.mystock.dao.HibernateDao;
import com.cyj.mystock.entity.Cdcp2r;
import com.cyj.mystock.entity.Cdcp2rVo;

@Repository
public class Cdcp2rDao<T> extends HibernateDao<T> {

	/**
	 * 查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cdcp2rVo> query() {
		String queryString = "SELECT cdsp2r.rq AS rq,cdsp2r.gpdm AS gpdm,cdsp2r.gpmc AS gpmc,cdsp2r.mrjg AS mrjg,"
				+ " cdsp2r.mcjg AS mcjg,(cdsp2r.mcjg - cdsp2r.mrjg) as ykje,cdsp2r.recid,"
				+ " CONCAT(ROUND((cdsp2r.mcjg - cdsp2r.mrjg)/cdsp2r.mrjg*100,3),'%') as zdl"
				+ " FROM cdsp2r  ORDER BY cdsp2r.rq DESC";
//		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		return (List<Cdcp2rVo>) queryAll(queryString, Cdcp2rVo.class); 
	}
	public void saveOrUpdate(Cdcp2r cdcp2r){
		super.saveOrUpdate(cdcp2r);
	}
	public void delete(String id){
		super.delete(id,Cdcp2r.class);
	}
}
