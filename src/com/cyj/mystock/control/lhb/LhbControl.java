/**
 * 
 */
package com.cyj.mystock.control.lhb;

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
@RequestMapping("/shlhb") 
public class LhbControl extends BaseControl{

	
	@RequestMapping("/getAll")  
    public String queryAll(HttpServletRequest request){  
        return "/shlhb/main";  
    }  
	
}
