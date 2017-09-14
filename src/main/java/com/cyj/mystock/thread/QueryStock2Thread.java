package com.cyj.mystock.thread;

import com.cyj.mystock.BeanUtils;
import com.cyj.mystock.cache.CcgpCache;
import com.cyj.mystock.entity.CcgpVO;
import com.cyj.mystock.service.ccgp.CcgpService;
import com.cyj.mystock.websocket.listener.WebsocketSendListener;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/9/7.
 */
@Component
public class QueryStock2Thread implements Runnable {
    private final String URL = "http://http://image.sinajs.cn/newchart/min/n/$code$.gif";
    @Autowired
    protected CcgpService ccgpService = BeanUtils.getBeanByName("ccgpService", CcgpService.class);
    @Autowired
    protected RedisTemplate redisTemplate = BeanUtils.getBeanByName("redisTemplate", RedisTemplate.class);

    protected WebsocketSendListener sendListener = new WebsocketSendListener();


    private static QueryStock2Thread instance = new QueryStock2Thread();

    public static QueryStock2Thread getInstance() {
        if (instance == null) {
            return new QueryStock2Thread();
        } else {
            return instance;
        }
    }

    @Override
    public void run() {
        System.out.println(new Date() + " 启动了查询股票行情走势图接口线程!");
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            List<String> codeList = new ArrayList<String>();
            Map<String, CcgpVO> map = CcgpCache.getAll();
            if(map==null||map.size()==0) {
                System.out.println(" CcgpCache is null !");
                List<CcgpVO> list = ccgpService.getAll();
//                    ccgpService.add(list);
                for (CcgpVO gp : list) {
                    StringBuffer sb = new StringBuffer();
                    if (gp.getCode().trim().startsWith("00") || gp.getCode().trim().startsWith("30")) {
                        sb.append("sz");
                    } else {
                        sb.append("sh");
                    }
                    sb.append(gp.getCode().trim());
                    codeList.add(sb.toString());
                }
            }else{
                System.out.println(" CcgpCache is not null !");
                for (String key:map.keySet()){
                    StringBuffer sb = new StringBuffer();
                    if (key.startsWith("00") || key.startsWith("30")) {
                        sb.append("sz");
                    } else {
                        sb.append("sh");
                    }
                    sb.append(key);
                    codeList.add(sb.toString());
                }
            }
            for(String code : codeList) {
                String getUrl = URL.replaceAll("$code$",code);
                HttpGet request = new HttpGet(getUrl);
                RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(15000)
                        .setConnectionRequestTimeout(30000).build();
                request.setConfig(requestConfig);

                CloseableHttpResponse response = httpclient.execute(request);
                org.apache.http.Header[] headers = response.getHeaders("Content-Type");

                byte[] content = EntityUtils.toByteArray(response.getEntity());
                sendListener.send(content);
            }
            System.out.println(new Date() + " 关闭了查询股票行情走势图接口!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
