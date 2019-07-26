/**
 * 
 */
package com.ljs.account.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lijunshi
 *
 */
@Component
public class ApplicationContextUtil<T> implements ApplicationContextAware {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 this.context = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}
	
	public static Object  getBeanByName(String beanName){
		return context.getBean(beanName);
	}

	public static <T>  T getBeanByClass(Class<T> classType) {
		return context.getBean(classType);
	}
}
