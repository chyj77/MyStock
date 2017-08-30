package com.cyj.mystock.dao.ccgp;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cyj.mystock.dao.HibernateDao;
import com.cyj.mystock.entity.Spcz;

@Repository
public class CcgpDao<T> extends HibernateDao<T> {

	/**
	 * 查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Spcz> query(Map<String,String> map) {
		String queryString = "SELECT shipancaozuo.rq AS rq,shipancaozuo.code AS code,shipancaozuo.name AS name,"
				+ "dictname AS caozuo,shipancaozuo.jiage AS jiage,shipancaozuo.sl AS sl,"
				+ "shipancaozuo.luoji AS luoji,recid"
				+ " FROM shipancaozuo join dict on caozuo = dictcode and dicttype='oper' ";
		String page = map.get("page");
		String rp = map.get("rp");
		String sortname = map.get("sortname");
		String sortorder = map.get("sortorder");
		String val = map.get("query");
		String qtype = map.get("qtype");
		String cond = " where 1=1 ";
		String limit= "";
		if(page!=null&&!"".equals(page)){
			if("1".equals(page)){
				limit =" LIMIT " + (Integer.parseInt(page)-1) +" , "+rp;
			}else{
				limit =" LIMIT " + (Integer.parseInt(page)-1)*Integer.parseInt(rp)+" , "
						+rp;
			}
		}
		if(map.get("query")!=null && !"".equals(map.get("query"))){
			cond+=" and "+qtype+"='"+val+"'";
		}
		String sort =" order by "+sortname+" " +sortorder;
		queryString +=cond + sort + limit;
		return (List<Spcz>) this.queryAll(queryString, Spcz.class); 
	}
	
	
	public BigInteger queryCount(Map<String,String> map){
		String queryString = "SELECT count(*) "
				+ " FROM shipancaozuo ";
		if(map.get("query")==null || "".equals(map.get("query"))){
			
		}else{
			String val = map.get("query");
			String qtype = map.get("qtype");
			
			queryString += " where "+qtype+"='"+val+"'";
		}
		return super.queryCount(queryString);
	}	
}
