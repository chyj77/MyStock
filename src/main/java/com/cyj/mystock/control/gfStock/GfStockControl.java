/**
 * 
 */
package com.cyj.mystock.control.gfStock;

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
import com.cyj.mystock.entity.GfStock;
import com.cyj.mystock.service.GfStock.GfStockService;

/**
 * @author Administrator
 *
 */
@Controller  
@RequestMapping("/gfStock") 
public class GfStockControl extends BaseControl{

	@Autowired
	private GfStockService service;

	@RequestMapping("/getAll")  
    public String queryAll(HttpServletRequest request){  
        return "/gfStock/main";  
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
		List<GfStock> list = service.query(map);
		Object o = JsonHelper.flexigridJson(list,size.intValue(),map.get("page"));
		outPrint(request,response,o);
	}
	
	@RequestMapping("/getGfstock")  
    public String getGfstock(HttpServletRequest request){  
        return "/gfStock/analytics";  
    }  
	
	@RequestMapping("/queryGfstock") 
	public void queryGfstock(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
		Map<String,Object> list = service.queryGfStock(map);
		Object o = JsonHelper.mapToJson(list);
		outPrint(request,response,o);
	}
	
	public GfStockService getService() {
		return service;
	}

	public void setService(GfStockService service) {
		this.service = service;
	}
}
