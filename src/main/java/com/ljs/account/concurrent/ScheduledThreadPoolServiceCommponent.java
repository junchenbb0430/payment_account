/**
 * 
 */
package com.ljs.account.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lijunshi
 *
 */
@Component
public class ScheduledThreadPoolServiceCommponent implements InitializingBean{

	/* 
	 * 初始化延迟时间  
	 * 1000
	 */
	@Value("${initialDelay}")
	private long initialDelay;
	/*
	 * 间隔时间-两次任务执行间隔
	 * 2000
	 */
	@Value("${scheduledPeriod}")
	private long period;
	/*
	 * 延迟时间
	 * 3000
	 */
	@Value("${scheduledDelay}")
	private long delay;
	
	public void executeTaskWithFixedRate(Runnable command) {
		scheduledExceutorService.scheduleAtFixedRate(command, initialDelay, period, TimeUnit.MILLISECONDS);
	}
	
	public void executeTaskWithFixedDelay(Runnable command) {
		scheduledExceutorService.scheduleWithFixedDelay(command, initialDelay, delay, TimeUnit.MILLISECONDS);
	}

	 
	
	private ScheduledExecutorService scheduledExceutorService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 
		scheduledExceutorService = new ScheduledThreadPoolExecutor(2);
	}
}
