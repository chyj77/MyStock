package com.cyj.mystock.dao.gfStock;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cyj.mystock.dao.HibernateDao;
import com.cyj.mystock.entity.GfStock;

@Repository
public class GfStockDao<T> extends HibernateDao<T> {

	/**
	 * 查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GfStock> query(Map<String,String> map) {
		String page = map.get("page");
		String rp = map.get("rp");
		String sortname = map.get("sortname");
		String sortorder = map.get("sortorder");
		String val = map.get("query");
		String qtype = map.get("qtype");
		String queryString = "SELECT *  FROM mystock ";
		String cond = " where 1=1 ";
		String limit=" LIMIT ";
		if("1".equals(page)){
			limit +=  (Integer.parseInt(page)-1) +" , "+rp;
		}else{
			limit +=  (Integer.parseInt(page)-1)*Integer.parseInt(rp)+" , "
					+rp;
		}
		if(map.get("query")!=null && !"".equals(map.get("query"))){
			cond+=" and "+qtype+"='"+val+"'";
		}
		String sort =" order by "+sortname+" " +sortorder;
		queryString +=cond + sort + limit;
		return (List<GfStock>) super.queryAll(queryString, GfStock.class); 
	}
	public BigInteger queryCount(Map<String,String> map){
		String queryString = "SELECT count(*) "
				+ " FROM mystock ";
		if(map.get("query")==null || "".equals(map.get("query"))){
			
		}else{
			String val = map.get("query");
			String qtype = map.get("qtype");
			
			queryString += " where "+qtype+"='"+val+"'";
		}
		return super.queryCount(queryString);
	}
	//_search=false&nd=1487400210196&rows=8&page=1&sidx=&sord=asc
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryGfStock(Map<String,String> map) {
		String page = map.get("page");
		String rp = map.get("rows");
		String sortname = map.get("sidx");
		String sortorder = map.get("sord");
		String searchField = map.get("searchField");
		String searchString = map.get("searchString");
		String searchOper = map.get("searchOper");
		String queryString = "select stockcode,stockname ,sum(stocknum) stocknum, sum(fsje) fsje,"
				+ "(sum(fsje)/abs(sum(case oper when '证券买入' then fsje  else 0 end)))*100 zdf"
				+ " from mystock ";
		String cond = " where oper in ('证券买入','证券卖出','红股入账')";
		String group = " GROUP BY stockcode,stockname ";
		String limit="";
		if("1".equals(page)){
			limit =" LIMIT " + (Integer.parseInt(page)-1) +" , "+rp;
		}else{
			limit =" LIMIT " + (Integer.parseInt(page)-1)*Integer.parseInt(rp)+" , "
					+rp;
		}
		if(map.get("searchField")!=null && !"".equals(map.get("searchField"))){
			cond+=" and "+searchField+"='"+searchString+"'";
		}
//		String sort =" order by "+sortname+" " +sortorder;
		String sort =" order by stocknum desc,fsje desc ";
		queryString +=cond + group + sort;
		String querycount = "select count(*) from (" + queryString +" ) a ";
		BigInteger count = super.queryCount(querycount);
		queryString += limit;
		List<Map<String,Object>> list = (List<Map<String,Object>>) super.queryAll(queryString); 
		Map<String,Object> countMap = new HashMap<String,Object>();
		countMap.put("rows", list);
		countMap.put("records", count);
		countMap.put("total", count.divide(BigInteger.valueOf(Long.parseLong(rp))));
		return countMap;
	}
}
