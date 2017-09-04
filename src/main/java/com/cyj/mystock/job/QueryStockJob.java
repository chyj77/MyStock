package com.cyj.mystock.job;

import java.util.Date;
import java.util.List;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyj.mystock.entity.Ccgp;
import com.cyj.mystock.service.ccgp.CcgpService;

public class QueryStockJob {

	@Autowired
	CcgpService ccgpService;

	private final String url = "http://hq.sinajs.cn/list=";

	public void execute() {
		try {
			List<Ccgp> list = ccgpService.query();
			StringBuffer sb = new StringBuffer();
			for (Ccgp gp : list) {
				if (gp.getCode().startsWith("00") || gp.getCode().startsWith("30")) {
					sb.append("sz");
				} else {
					sb.append("sh");
				}
				sb.append(gp.getCode()).append(",");
			}
			String getUrl = url + sb.toString();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			while (true) {
				HttpGet request = new HttpGet(getUrl);
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(15000)
						.setConnectionRequestTimeout(30000).build();
				request.setConfig(requestConfig);

				CloseableHttpResponse response = httpclient.execute(request);
				org.apache.http.Header[] headers = response.getHeaders("Content-Type");

				String content = EntityUtils.toString(response.getEntity());
				System.out.println(new Date() + content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
