package com.cyj.mystock.service.cdcp2r;

import com.cyj.mystock.dao.cdcp2r.Cdcp2rDao;
import com.cyj.mystock.entity.Cdcp2r;
import com.cyj.mystock.entity.Cdcp2rVo;
import com.cyj.mystock.service.ccgp.CcgpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(transactionManager="transactionManager")
public class Cdcp2rService {

	@Autowired
	private Cdcp2rDao<?> dao;
	@Autowired
	CcgpService ccgpService;
	
	public Cdcp2rDao<?> getDao() {
		return dao;
	}	

	public void setDao(Cdcp2rDao<?> dao) {
		this.dao = dao;
	}

	

	public List<Cdcp2rVo> query() {
		return dao.query();
	}
	
	public List<Map<String,Object>> queryWeek() throws Exception {
		List<Cdcp2rVo> voList =  dao.query();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int size = voList.size();
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>(size);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Cdcp2rVo vo:voList){
			Map<String,Object> map = new HashMap<String,Object>();
			String rq = vo.getRq();
			String ykje= vo.getYkje();
			String zdl= vo.getZdl();
			zdl =zdl==null?null:zdl.replaceAll("%","");
			Date date = sdf.parse(rq);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int week = cal.get(Calendar.WEEK_OF_YEAR);
			if(month==12 && week==1){
				year = year+1;
			}
			map.put("year", year);
			map.put("month", month);
			map.put("week", week);
			map.put("ykje", ykje);
			map.put("zdl", zdl);
			tempList.add(map);
		}
		for(int i=0;i<size;i++){
			if(i==(size-1)) break;
			Map<String,Object> map1 = tempList.get(i);
			Map<String,Object> map2 = tempList.get(i+1);
			if(map1.get("year").equals(map2.get("year")) && map1.get("week").equals(map2.get("week"))){
				String sykje1 =map1.get("ykje")==null?null: map1.get("ykje").toString();
				String sykje2 =map2.get("ykje")==null?null: map2.get("ykje").toString();	
				BigDecimal ykje1 =sykje1==null?null:new BigDecimal(sykje1);
				BigDecimal ykje2 =sykje2==null?null: new BigDecimal(sykje2);
				String szdl1 =map1.get("zdl")==null?"":map1.get("zdl").toString();
				String szdl2 =map2.get("zdl")==null?"":map2.get("zdl").toString();				
				BigDecimal zdl1 = null; 
				BigDecimal zdl2 = null; 
				if(!"".equals(szdl1)){
					 zdl1= new BigDecimal(szdl1.replaceAll("%",""));
				}
				if(!"".equals(szdl2)){
					zdl2= new BigDecimal(szdl2.replaceAll("%",""));
				}
				if(zdl1==null){
					map2.put("zdl", szdl2.replaceAll("%",""));
					list.add(map2);
				}else{
					BigDecimal ykje = ykje2==null?null:ykje1.add(ykje2);
					BigDecimal zdl = zdl2==null?null:zdl1.add(zdl2);
					map2.put("year", map2.get("year"));
					map2.put("month",map2.get("month"));
					map2.put("week",map2.get("week"));
					map2.put("ykje",ykje);
					map2.put("zdl",zdl==null?null:zdl);	
					if(list.size()>0){
						if(list.contains(map1)){
							list.remove(map1);
						}
					}
					list.add(map2);
				}
			}else if(map1.get("year").equals(map2.get("year")) && !map1.get("week").equals(map2.get("week"))){
				if(i==0){
					list.add(map1);
				}else if(i==size-2){
					String szdl2 =map2.get("zdl")==null?"":map2.get("zdl").toString();	
					map2.put("zdl", szdl2.replaceAll("%",""));
					list.add(map2);
				}
			}else if(!map1.get("year").equals(map2.get("year"))){
				if(i==0){
					list.add(map1);
				}else if(i==size-1){
					list.add(map2);
				}
			}
		}
		return list;
	}
	
	public void saveOrUpdate(Cdcp2r cdcp2r){
		this.dao.saveOrUpdate(cdcp2r);
		ccgpService.reload();
	}
	public void delete(String id){
		this.dao.delete(id);
		ccgpService.reload();
	}
}
