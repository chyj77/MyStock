/**
 * 
 */
package com.cyj.mystock.control.spcz;

import com.cyj.mystock.Utils.JsonHelper;
import com.cyj.mystock.Utils.StringHelper;
import com.cyj.mystock.control.BaseControl;
import com.cyj.mystock.entity.Spcz;
import com.cyj.mystock.service.spcz.SpczService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
@Controller  
@RequestMapping("/spcz") 
public class SpczControl extends BaseControl{

	@Autowired
	private SpczService service;

	@RequestMapping("/getAll")  
    public String queryAll(HttpServletRequest request){  
        return "/spcz/main";  
    }  
	
	@RequestMapping("/getAllAjax") 
	public void queryAllAjax(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String params=StringHelper.getRequestPayload(request);
		Map<String,String> map = new HashMap<String,String>();
		String[] param = params.split("&");
		for(String pas:param){
			String[] pa = pas.split("=");
			if(pa.length==1){
				map.put(pa[0], "");
			}else
				map.put(pa[0], pa[1]);
		}
		BigInteger size = service.queryCount(map);
		List<Spcz> list = service.query(map);
		Object o = JsonHelper.flexigridJson(list,size.intValue(),map.get("page"));
		outPrint(request,response,o);
	}
	
	@RequestMapping("/modify")  
    public String modify(HttpServletRequest request,HttpServletResponse response) throws IOException{  
        return "/spcz/modify";  
    }  
	@RequestMapping("/toEdit")  
	public void toEdit(HttpServletRequest request,HttpServletResponse response) throws IOException{  
		String json=StringHelper.getRequestPayload(request);
		JSONObject jso = JSONObject.fromObject(json);
		String recid = jso.getString("recid");
		if(recid!=null&&!"".equals(recid)&&!"null".equals(recid)){
			Spcz spcz=service.queryById(recid);
			JSONObject jo = JSONObject.fromObject(spcz);
			outPrint(request,response,jo);
		}
    }  
	
	/**
	 * 
	 * @param
	 * @throws IOException 
	 */
	@RequestMapping("/doSave")
    public void doSave(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try{
			String json=StringHelper.getRequestPayload(request);
			JSONObject jo = JSONObject.fromObject(json);
			Spcz spcz = new Spcz();
			spcz.setRq(StringHelper.getNull(jo.getString("rq")));
			spcz.setRecid(StringHelper.getNull(jo.getString("recid")));
			spcz.setCode(StringHelper.getNull(jo.getString("code")));
			spcz.setName(StringHelper.getNull(jo.getString("name")));
			spcz.setCaozuo(StringHelper.getNull(jo.getString("caozuo")));
			spcz.setJiage(StringHelper.getDouble(jo.getString("jiage")));
			spcz.setSl(StringHelper.getDouble(jo.getString("sl")));
			spcz.setLuoji(StringHelper.getNull(jo.getString("luoji")));
			service.saveOrUpdate(spcz);
	        Object o = JsonHelper.resultJson("保存成功",true);
	        outPrint(request,response,o);
		}catch(Exception e){
			e.printStackTrace();
			Object o = JsonHelper.resultJson("保存失败",false);
	        outPrint(request,response,o);
		}
    }
	@RequestMapping("/doDel")
    public void doDel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			String json=StringHelper.getRequestPayload(request);
			JSONArray jo = (JSONArray) JsonHelper.jsonArray(json);
			service.deleteBatch(jo);
	        Object o = JsonHelper.resultJson("成功删除"+jo.size()+"条记录",true);
	        outPrint(request,response,o);
		}catch(Exception e){
			Object o = JsonHelper.resultJson("删除失败",false);
	        outPrint(request,response,o);
		}
    }

	@RequestMapping("/getStat")  
    public String getStat(HttpServletRequest request){  
        return "/spcz/stat";  
    }  
	
	@RequestMapping("/queryStat") 
	public void queryStat(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Map<String,Object>> list = service.queryStat();
		Object o = JsonHelper.listToJson(list);
		outPrint(request,response,o);
	}
	
	@RequestMapping("/queryDetail") 
	public void queryDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String params=StringHelper.getRequestPayload(request);
		Map<String,String> map = new HashMap<String,String>();
		String[] param = params.split("&");
		for(String pas:param){
			String[] pa = pas.split("=");
			if(pa.length==1){
				map.put(pa[0], "");
			}else
				map.put(pa[0], pa[1]);
		}
		if(map.get("code")!=null&&!"".equals(map.get("code"))){
			List<Spcz> list = service.queryDetail(map.get("code"));
			Object o = JsonHelper.listToJson(list);
			outPrint(request,response,o);
		}else{
			outPrint(request,response,"{}");
		}
	}
	
	public SpczService getService() {
		return service;
	}

	public void setService(SpczService service) {
		this.service = service;
	}
}
