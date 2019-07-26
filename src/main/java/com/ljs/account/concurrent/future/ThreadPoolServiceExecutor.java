/**
 * 
 */
package com.ljs.account.concurrent.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lijunshi
 * 线程池执行器：定义了各种执行任务的方式
 * @param <V>
 */
@Component
public class ThreadPoolServiceExecutor<V> implements InitializingBean{

	private ExecutorService executorService;
	
	@Value("${corePoolSize}")
	private int corePoolSize;
	
	@Value("${maxPoolSize}")
	private int maxPoolSize;
	
	@Value("${keepAliveTime}")
	private long keepAliveTime;
	
	/**
	 * 队列深度
	 */
	@Value("${queueDepth}")
	private int queueDepth;
	 
	 /**
	  * 
	 * @param task，其中V代表返回的执行结果
	 * @return 直接返回任务的执行结果，
	 * @throws InterruptedException
	 * @throws ExecutionException
	  */
	public   V  executeTaskReturnResult(Callable<V> task) throws InterruptedException, ExecutionException {
		
		 Future<V> future =  executorService.submit(task);
		 return future.get();
		 
	}
	/**
	 * 任务的执行结果，通过FutureTask.get()方法获取
	* @param futureTask 其中V代表返回的执行结果
	 */
	public void executeFutureTask(FutureTask<V> futureTask) {
		executorService.submit(futureTask);
	}
	/**
	 * 
	* @param task
	 */
	public void executeTask(Runnable task) {
		executorService.execute(task);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 没有添加拒绝策略
		// 队列深度
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(queueDepth);
		executorService = new ThreadPoolExecutor(corePoolSize
							,maxPoolSize
							,keepAliveTime
							,TimeUnit.MILLISECONDS
							,queue
							,Executors.defaultThreadFactory());
	}
}
