package com.cyj.mystock;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MystockContextAware implements ApplicationContextAware {

//	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
//		BeanUtils.setCtx(arg0);
//	}

	public void setApplicationContext(org.springframework.context.ApplicationContext arg0) throws BeansException {
		BeanUtils.setCtx(arg0);
		
	}

}
