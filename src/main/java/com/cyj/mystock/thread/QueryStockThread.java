package com.cyj.mystock.thread;

import com.cyj.mystock.BeanUtils;
import com.cyj.mystock.cache.CcgpCache;
import com.cyj.mystock.entity.CcgpVO;
import com.cyj.mystock.jettywebsocket.AdapterEchoSocket;
import com.cyj.mystock.service.ccgp.CcgpService;
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
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/7.
 */
@Component
public class QueryStockThread implements Runnable {
    private final String url = "http://hq.sinajs.cn/list=";
    @Autowired
    protected CcgpService ccgpService = BeanUtils.getBeanByName("ccgpService", CcgpService.class);
    @Autowired
    protected RedisTemplate redisTemplate = BeanUtils.getBeanByName("redisTemplate", RedisTemplate.class);

//    protected WebsocketSendListener sendListener = new WebsocketSendListener();

    public AdapterEchoSocket getAdapterEchoSocket() {
        return adapterEchoSocket;
    }

    public void setAdapterEchoSocket(AdapterEchoSocket adapterEchoSocket) {
        this.adapterEchoSocket = adapterEchoSocket;
    }

    protected AdapterEchoSocket adapterEchoSocket;

    public static boolean IsBreak = true;

    private static QueryStockThread instance = new QueryStockThread();

    public static QueryStockThread getInstance() {
        if (instance == null) {
            return new QueryStockThread();
        } else {
            return instance;
        }
    }

    @Override
    public void run() {
        System.out.println(new Date() + " 启动了查询股票行情接口线程!");
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            Map<String, CcgpVO> map = CcgpCache.getAll();
            StringBuffer sb = new StringBuffer();
            if (map == null || map.size() == 0) {
                System.out.println(" CcgpCache is null !");
                List<CcgpVO> list = ccgpService.getAll();
//                    ccgpService.add(list);
                for (CcgpVO gp : list) {
                    if (gp.getCode().startsWith("00") || gp.getCode().startsWith("30")) {
                        sb.append("sz");
                    } else {
                        sb.append("sh");
                    }
                    sb.append(gp.getCode()).append(",");
                }
            } else {
                System.out.println(" CcgpCache is not null !");
                for (String key : map.keySet()) {
                    if (key.startsWith("00") || key.startsWith("30")) {
                        sb.append("sz");
                    } else {
                        sb.append("sh");
                    }
                    sb.append(key).append(",");
                }
            }
            String getUrl = url + sb.toString();
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (IsBreak) {
                HttpGet request = new HttpGet(getUrl);
                RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(15000)
                        .setConnectionRequestTimeout(30000).build();
                request.setConfig(requestConfig);
                String content = null;
                try {
                    CloseableHttpResponse response = httpclient.execute(request);
                    org.apache.http.Header[] headers = response.getHeaders("Content-Type");

                    content = EntityUtils.toString(response.getEntity());
//                System.out.println(date + "-->content:" + content);
                    String[] stockhqs = content.split(";");
                    for (String stockhqstr : stockhqs) {
                        if (StringUtils.isNotBlank(stockhqstr)) {
                            String[] stockhqstrs = stockhqstr.split(",");
                            String stockCode = stockhqstrs[0].trim().substring(13, 19);
                            String nowprice = stockhqstrs[3].trim();
                            CcgpVO ccgpVO = ccgpService.get(stockCode);
                            String rq = ccgpVO.getRq();
                            Date ccrq = sdf.parse(rq);
                            int ccday = daysBetween(ccrq, now);
                            ccgpVO.setCcday(String.valueOf(ccday));
                            ccgpVO.setNowprice(nowprice);
                            String sl = ccgpVO.getSl();
                            BigDecimal d_sl = new BigDecimal(1);
                            BigDecimal d_nowprice = new BigDecimal(0);
                            BigDecimal d_buyprice = new BigDecimal(0);
                            BigDecimal d_yke = new BigDecimal(0);
                            BigDecimal d_zdl = new BigDecimal(0);
                            if (StringUtils.isNotBlank(sl)) {
                                d_sl = new BigDecimal(sl);
                            }
                            if (StringUtils.isNotBlank(nowprice)) {
                                d_nowprice = new BigDecimal(nowprice);
                            }
                            String buyprice = ccgpVO.getBuyprice();
                            if (StringUtils.isNotBlank(buyprice)) {
                                d_buyprice = new BigDecimal(buyprice);
                            }
                            d_yke = (d_nowprice.subtract(d_buyprice)).multiply(d_sl).setScale(3);
                            d_zdl = (d_nowprice.subtract(d_buyprice)).divide(d_buyprice, 3, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(3);
                            ccgpVO.setYke(d_yke.toString());
                            ccgpVO.setZdl(d_zdl.toString());
                            ccgpService.update(ccgpVO);
                            JSONObject jsonObject = JSONObject.fromObject(ccgpVO);
                            String temp = jsonObject.toString();
                            if( adapterEchoSocket!=null && adapterEchoSocket.getRemote()!=null) {
                                adapterEchoSocket.getRemote().sendStringByFuture(jsonObject.toString());
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!IsBreak) {
                    System.out.println("donot see me !");
                    break;
                }
                Thread.sleep(1000 * 2);
            }
            System.out.println(new Date() + " 关闭了查询股票行情接口!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int daysBetween(Date ccrq, Date now) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ccrq = sdf.parse(sdf.format(ccrq));
        now = sdf.parse(sdf.format(now));
        Calendar cal = Calendar.getInstance();
        cal.setTime(ccrq);
        long time1 = cal.getTimeInMillis();
        cal.setTime(now);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }
}
