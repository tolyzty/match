package com.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(SpringContext.class);
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContext.applicationContext = applicationContext;
		log.info("ApplicationContext registed");
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		checkApplicationContext();
		return applicationContext.getBean(name, clazz);
	}

	public static Object getBean(String name) {
		checkApplicationContext();
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		Map<String,T> beanMaps = applicationContext.getBeansOfType(clazz);
		if ((beanMaps != null) && (!beanMaps.isEmpty())) {
			return beanMaps.values().iterator().next();
		}
		return null;
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
//			ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext();
			String eString = "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil";
			IllegalStateException exception = new IllegalStateException(eString);
			log.error(eString, exception);
			throw exception;
		}
	}
}