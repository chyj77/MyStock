package com.cyj.mystock.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseControl {

	public void outPrint(HttpServletRequest request,HttpServletResponse response,Object result) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
        out.flush();
        out.close();
	}
}
