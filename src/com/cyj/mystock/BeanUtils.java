package com.cyj.mystock;

import org.springframework.context.ApplicationContext;

public class BeanUtils {

	private static ApplicationContext ctx;

	public static ApplicationContext getCtx() {
		if(ctx == null){
			throw new RuntimeException("spring上下文未初始化！");
		}
		return ctx;
	}

	public static void setCtx(ApplicationContext ctx) {
		BeanUtils.ctx = ctx;
	}
	

	@SuppressWarnings("unchecked")
	public static <T> T getBeanByName(String name,Class<T> clz){
		return (T)getCtx().getBean(name, clz);
	}
}
