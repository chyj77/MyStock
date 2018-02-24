package com.cyj.mystock.dao.ccgp;

import com.cyj.mystock.dao.HibernateDao;
import com.cyj.mystock.entity.Ccgp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CcgpDao<T> extends HibernateDao<T> {

	/**
	 * 查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ccgp> query(Map<String,String> map) {
		String queryString = "select * from ccgp";
		String page = map.get("page");
		String rp = map.get("rp");
		String sortname = map.get("sortname");
		String sortorder = map.get("sortorder");
		String val = map.get("query");
		String qtype = map.get("qtype");
		String cond = " and 1=1 ";
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
		return (List<Ccgp>) this.queryAll(queryString, Ccgp.class); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Ccgp> query(){
		String queryString = "select * from ccgp";
		return (List<Ccgp>) this.queryAll(queryString, Ccgp.class); 
	}
	
	
	public BigInteger queryCount(Map<String,String> map){
		String queryString = "SELECT count(*) "
				+ " FROM ccgp ";
		if(map.get("query")==null || "".equals(map.get("query"))){
			
		}else{
			String val = map.get("query");
			String qtype = map.get("qtype");
			
			queryString += " where "+qtype+"='"+val+"'";
		}
		return super.queryCount(queryString);
	}

}
