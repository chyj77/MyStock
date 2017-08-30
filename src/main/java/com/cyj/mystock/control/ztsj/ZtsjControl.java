/**
 * 
 */
package com.cyj.mystock.control.ztsj;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyj.mystock.Utils.JsonHelper;
import com.cyj.mystock.Utils.StringHelper;
import com.cyj.mystock.control.BaseControl;
import com.cyj.mystock.entity.Ztsj;
import com.cyj.mystock.service.ztsj.ZtsjService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
@Controller  
@RequestMapping("/ztsj") 
public class ZtsjControl extends BaseControl{

	@Autowired
	private ZtsjService service;

	@RequestMapping("/getAll")  
    public String queryAll(HttpServletRequest request){  
        return "/ztsj/main";  
    }  
	@RequestMapping("/analytics")  
	public String queryAnaly(HttpServletRequest request){  
		return "/ztsj/analytics";  
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
		List<Ztsj> list = service.query(map);
		Object o = JsonHelper.flexigridJson(list,size.intValue(),map.get("page"));
		outPrint(request,response,o);
	}
	
	@RequestMapping("/modify")  
    public String modify(HttpServletRequest request,HttpServletResponse response) throws IOException{  
        return "/ztsj/modify";  
    }  
	@RequestMapping("/toEdit")  
	public void toEdit(HttpServletRequest request,HttpServletResponse response) throws IOException{  
		String json=StringHelper.getRequestPayload(request);
		JSONObject jso = JSONObject.fromObject(json);
		String recid = jso.getString("recid");
		if(recid!=null&&!"".equals(recid)&&!"null".equals(recid)){
			Ztsj ztsj=service.queryById(recid);
			JSONObject jo = JSONObject.fromObject(ztsj);
			outPrint(request,response,jo);
		}
    }  
	
	/**
	 * 
	 * @param Cdcp2r
	 * @throws IOException 
	 */
	@RequestMapping("/doSave")
    public void doSave(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try{
			String json=StringHelper.getRequestPayload(request);
			JSONObject jo = JSONObject.fromObject(json);
			Ztsj ztsj = new Ztsj();
			ztsj.setMrztgs(StringHelper.getInt(jo.getString("mrztgs")));
			ztsj.setFyzbgs(StringHelper.getInt(jo.getString("fyzbgs")));
			ztsj.setRq(StringHelper.getNull(jo.getString("rq")));
			ztsj.setDqztgs(StringHelper.getInt(jo.getString("dqztgs")));
			ztsj.setDqztgkl(StringHelper.getDouble(jo.getString("dqztgkl")));
			ztsj.setRecid(StringHelper.getNull(jo.getString("recid")));
			ztsj.setZtzdgn(StringHelper.getNull(jo.getString("ztzdgn")));
			ztsj.setZtzdgs(StringHelper.getInt(jo.getString("ztzdgs")));
			ztsj.setDbcrgkl(StringHelper.getDouble(jo.getString("dbcrgkl")));
			ztsj.setSpcgl(StringHelper.getDouble(jo.getString("spcgl")));
			ztsj.setBzsl(StringHelper.getInt(jo.getString("bzsl")));
			ztsj.setBzl(StringHelper.getDouble(jo.getString("bzl")));
			service.saveOrUpdate(ztsj);
	        Object o = JsonHelper.resultJson("保存成功",true);
	        outPrint(request,response,o);
		}catch(Exception e){
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
	
	@RequestMapping("/queryAnalytics") 
	public void queryAnalytics(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Ztsj> list = service.query();
		List<Ztsj> analyList = service.queryAnalytics();
		String records = String.valueOf(list.size());
		String total ="1";
		String rownum = String.valueOf(list.size());
		String page = "1";
		Object o = JsonHelper.ligerJson(analyList.get(0),list,records,total,rownum,page);
		outPrint(request,response,o);
	}
	
	
	public ZtsjService getService() {
		return service;
	}

	public void setService(ZtsjService service) {
		this.service = service;
	}
}
