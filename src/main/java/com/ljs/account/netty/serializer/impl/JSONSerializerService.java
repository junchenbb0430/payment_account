/**
 * 
 */
package com.ljs.account.netty.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljs.account.netty.serializer.SerializerAlgoritm;
import com.ljs.account.netty.serializer.SerializerService;

/**
 * @author lijunshi
 * json序列化
 */
public class JSONSerializerService implements SerializerService {

	@Override
	public byte getSerializerAlgoritm() {
		 
		return SerializerAlgoritm.JSONAlgoritm;
	}

	@Override
	public byte[] serializer(Object object) {
		 
		return JSON.toJSONBytes(object);
	}

	@Override
	public <T> T deserializer(byte[] bytes, Class<T> clazz) {
		 
		return JSON.parseObject(bytes, clazz);
	}

}
