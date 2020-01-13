/**
 * 
 */
package com.ljs.account.netty.serializer;

import com.ljs.account.netty.serializer.impl.JSONSerializerService;

/**
 * @author lijunshi
 * 定义序列化算法
 */
public interface SerializerService {
	
	SerializerService deault = new  JSONSerializerService();
	
	
	/**
	 * 获取序列化算法
	 * @return
	 */
	byte  getSerializerAlgoritm();
	
	
	/**
	 * 序列化对象，java对象---->byte[]
	 * @param t
	 * @return
	 */
	byte[]  serializer(Object  object);
	
	
	/**
	 * 反序列化，byte[]----->序列化对象
	 * @param <T>
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	<T> T  deserializer(byte[] bytes,Class<T> clazz);
}
