/**
 * 
 */
package com.cyj.mystock.control.cdcp2r;

import java.io.IOException;
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
import com.cyj.mystock.entity.Cdcp2r;
import com.cyj.mystock.entity.Cdcp2rVo;
import com.cyj.mystock.service.cdcp2r.Cdcp2rService;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
@Controller  
@RequestMapping("/cdcp2r") 
public class Cdcp2rControl extends BaseControl{

	@Autowired
	private Cdcp2rService service;

	@RequestMapping("/getAll")  
    public String queryAll(HttpServletRequest request){  
          
//        request.setAttribute("list", service.query());  
          
        return "/cdsp2r/main";  
    }  
	
	@RequestMapping("/getAllAjax") 
	public void queryAllAjax(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Cdcp2rVo> list = service.query();
		Object o = JsonHelper.listToJson(list);
		outPrint(request,response,o);
	}
	
	@RequestMapping("/getWeek")  
    public String queryWeek(HttpServletRequest request){  
          
//        request.setAttribute("list", service.query());  
          
        return "/cdsp2r/week";  
    }  
	
	@RequestMapping("/getWeekAnay") 
	public void queryWeekAnay(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Map<String,Object>> list = service.queryWeek();
		Object o = JsonHelper.listToJson(list);
		outPrint(request,response,o);
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
			Cdcp2r cdcp2r = new Cdcp2r();
			cdcp2r.setGpdm(StringHelper.getNull(jo.getString("gpdm")));
			cdcp2r.setGpmc(StringHelper.getNull(jo.getString("gpmc")));
			cdcp2r.setRq(StringHelper.getNull(jo.getString("rq")));
			cdcp2r.setMrjg(StringHelper.getNull(jo.getString("mrjg")));
			cdcp2r.setMcjg(StringHelper.getNull(jo.getString("mcjg")));
			cdcp2r.setRecid(StringHelper.getInt(jo.getString("recid")));
			service.saveOrUpdate(cdcp2r);
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
			JSONObject jo = JSONObject.fromObject(json);
			service.delete(jo.getString("recid"));
	        Object o = JsonHelper.resultJson("成功删除一条记录",true);
	        outPrint(request,response,o);
		}catch(Exception e){
			Object o = JsonHelper.resultJson("删除失败",false);
	        outPrint(request,response,o);
		}
    }
	

	
	
	public Cdcp2rService getService() {
		return service;
	}

	public void setService(Cdcp2rService service) {
		this.service = service;
	}
}
