package com.cyj.mystock.dao.spcz;

import com.cyj.mystock.dao.HibernateDao;
import com.cyj.mystock.entity.Spcz;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SpczDao<T> extends HibernateDao<T> {

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
//		List<Spcz> list =(List<Spcz>) this.queryAll(queryString, Spcz.class);
//		for(Spcz spcz:list) {
//			System.out.println("SpczDao:"+spcz.getName());
//			System.out.println("SpczDao:"+spcz.getLuoji());
//		}
		return (List<Spcz>) this.queryAll(queryString, Spcz.class); 
	}
	public void saveOrUpdate(Spcz spcz){
		super.saveOrUpdate(spcz);
	}
	public void delete(String id){
		super.delete(id,Spcz.class);
	}
	public Spcz queryById(String id){
		return (Spcz) super.queryById(id, Spcz.class);
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
	/**
	 * 实盘操作的统计
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryStat(){
		String sql ="SELECT shipancaozuo.code AS code,shipancaozuo.name AS name,sum(shipancaozuo.sl)*-1 AS sl,"
				+ "sum(shipancaozuo.jiage*abs(shipancaozuo.sl)) AS ykje, "
				+"datediff((case sum(shipancaozuo.sl)  when 0 then max(rq) else now()  end) ,min(rq)) ccts "
				+ " FROM shipancaozuo group by code,name ORDER BY sl desc,ykje desc";
		return (List<Map<String, Object>>) super.queryAll(sql);
	}
	/**
	 * 查询明细
	 * @param code
	 * @return
	 */
	public List<Spcz> queryDetail(String code){
		Map<String,String> map = new HashMap<String,String>();
		map.put("qtype", "code");
		map.put("query", code);
		map.put("sortname", "rq");
		map.put("sortorder", "desc");
		return this.query(map);
	}
}
