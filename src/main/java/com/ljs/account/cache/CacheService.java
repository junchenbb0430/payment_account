/**
 * 
 */
package com.ljs.account.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author lijunshi
 *
 */
 
public class CacheService {

	public static void main(String[] args) throws ExecutionException {
		Cache<String,String> cache = CacheBuilder.newBuilder().maximumSize(1000)
				.build();
	String value = 	cache.getIfPresent("zhang");
	
	System.out.println(value);
		try {
			value = cache.get("zhang", new Callable<String>() {
	
				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return "张三";
				}});
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(value);
		LoadingCache<String,String> loadingCache = CacheService.createLoadingCache();
		String value3 = loadingCache.get("5");
		 System.out.println(value3);
		  value3 = loadingCache.get("5");
		  System.out.println(value3);
		//		String name = loadingCache.get("2");
//		System.out.println(name);
//		name = loadingCache.get("2");
//		System.out.println(name);
//		name = loadingCache.get("1");
//		System.out.println(name);
//		name= loadingCache.getUnchecked("3");
//		System.out.println(name);
//		name= loadingCache.get("3");
//		System.out.println(name);
//		for(int i=0;i<200;i++) {
//			Thread t1 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						 
//						String value = loadingCache.get("3");
//						String threadName = Thread.currentThread().getName();
//						System.out.println("线程["+threadName+"]获取3的value值："+value);
//						 
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				
//			});
//			t1.start();
//		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static LoadingCache<String,String>  createLoadingCache(){
		LoadingCache<String, String> loadingCache =  CacheBuilder.newBuilder()
				.maximumSize(1000)//存储对象最大个数
				.concurrencyLevel(8)//并发级别，即cache中的segement的段数
				//.expireAfterWrite(5, TimeUnit.SECONDS)//过期策略
				.refreshAfterWrite(5,TimeUnit.MINUTES)
				.build(new CacheLoader<String,String>(){
			
			/**
			 * 缓存中不存在，则重新加载至缓存中
			 */
			@Override
			public String load(String key) throws Exception {
				String threadName = Thread.currentThread().getName();
				System.out.println("线程["+threadName+"]重新获取缓存，缓存中不存在 ,重新加载key="+key+"至缓存中");
				if("3".equals(key)) {
					return "王五";
				} 
				 
				return null;
			}
	
		});
		
		
		loadingCache.put("2","李四");
		loadingCache.put("1","张三");
		return loadingCache;
	}
}
