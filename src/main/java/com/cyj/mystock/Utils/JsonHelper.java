package com.cyj.mystock.Utils;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonHelper {

	public static Object listToJson(List<?> list){
		JSONArray jsonArray = JSONArray.fromObject(list); 
		return jsonArray;
	}
	public static Object mapToJson(Map<String,Object> map){
		JSONArray jsonArray = JSONArray.fromObject(map.get("rows")); 
		JSONObject jo = new JSONObject();
		jo.put("records", map.get("records"));
		jo.put("total",map.get("total"));
		jo.put("rows",jsonArray);
		return jo;
	}
	
	public static Object resultJson(String msg,boolean flag){
		JSONObject jo = new JSONObject();
		jo.put("message", msg);
		jo.put("success",flag);
		return jo;
	}
	
	public static Object flexigridJson(Object obj,int size){
		JSONObject jo = new JSONObject();
		jo.put("page", "1");
		jo.put("total",size);
		jo.put("rows",obj);
		return jo;
	}
	public static Object flexigridJson(Object obj,int size,String page){
		JSONObject jo = new JSONObject();
		jo.put("page", page);
		jo.put("total",size);
		jo.put("rows",obj);
		return jo;
	}
	
	public static Object ligerJson(Object obj1,Object obj2,String records,String total,String rownum,String page){
		JSONObject jo = new JSONObject();
		jo.put("userdata", obj1);
		jo.put("rows",obj2);
		jo.put("records",records);//总数
		jo.put("total",total);//页数
		jo.put("rownum",rownum);
		jo.put("page",page);
		return jo;
	}
	
	
	
	public static Object jsonArray(String json){
		JSONArray jsonArray = JSONArray.fromObject(json);
		return jsonArray;
		
	}
}
