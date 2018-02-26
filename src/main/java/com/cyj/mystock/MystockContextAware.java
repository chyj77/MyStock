package com.cyj.mystock;

import com.cyj.mystock.thread.QueryStockThread;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MystockContextAware implements ApplicationContextAware {

//	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
//		BeanUtils.setCtx(arg0);
//	}

	public void setApplicationContext(org.springframework.context.ApplicationContext arg0) throws BeansException {
		BeanUtils.setCtx(arg0);
		QueryStockThread thread = QueryStockThread.getInstance();
		QueryStockThread.IsBreak=true;
		SimpleDateFormat format = new SimpleDateFormat("HHmm");
		Date date = new Date();
		String nowDateValue = format.format(date);
		int nowDate = Integer.parseInt(nowDateValue);
		if(nowDate>915){
			new Thread(thread).start();
		}
	}

}
