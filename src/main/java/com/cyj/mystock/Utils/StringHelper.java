package com.cyj.mystock.Utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class StringHelper {

	/**
	 * 为空或null时 返回null值
	 * 
	 * @param arg0
	 * @return
	 */
	public static String getNull(String arg0) {
		if (arg0 == null)
			return arg0;
		if ("".equals(arg0.trim()))
			return null;
		if ("null".equalsIgnoreCase(arg0))
			return null;
		return arg0;
	}

	/**
	 * getRequestPayload 内容
	 * 
	 * @param req
	 * @return
	 */
	public static String getRequestPayload(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = req.getReader();
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static  String getInt(String id){
		try{
			int i = Integer.parseInt(id);
		}catch(Exception e){
			return null;
		}
		return id;
	}
	
	public static  String getDouble(String val){
		try{
			double i = Double.parseDouble(val);
		}catch(Exception e){
			return null;
		}
		return val;
	}
}
