/**
 * 
 */
package com.cyj.mystock.control.ccgp;

import com.cyj.mystock.Utils.JsonHelper;
import com.cyj.mystock.Utils.StringHelper;
import com.cyj.mystock.cache.ClientCache;
import com.cyj.mystock.control.BaseControl;
import com.cyj.mystock.entity.Spcz;
import com.cyj.mystock.service.ccgp.CcgpService;
import com.cyj.mystock.service.spcz.SpczService;
import com.cyj.mystock.websocket.handler.WebsocketMsgHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.websocket.common.Opcode;
import org.tio.websocket.common.WsPacket;
import org.tio.websocket.common.WsRequestPacket;
import org.tio.websocket.common.WsResponsePacket;

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
@RequestMapping("/ccgp")
public class CcgpControl extends BaseControl{

	@Autowired
	private CcgpService service;

	@RequestMapping("/getAll")
    public String queryAll(HttpServletRequest request){
        return "/ccgp/main";
    }  
	
	@RequestMapping("/getAllAjax")
	public void queryAllAjax(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	List list =	service.getAll();
		Object o = JsonHelper.flexigridJson(list,list.size());
		outPrint(request,response,o);
	}
}
