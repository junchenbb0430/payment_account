package com.ljs.account.json;

import com.alibaba.fastjson.JSONObject;

public class JSONParseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json = "{\"bankSeq\":\"20190718150919033100000138\",\"channelId\":\"NUCC_epcc\",\"channelType\":\"0331\",\"errorType\":\"6301\",\"msgKey\":\"check_error_20190718151038_337075133029\",\"oriPaymentSeq\":\"2019070400000000003621690210406\",\"paymentSeq\":\"20190718150919033100000138\",\"requestTrxId\":\"2019070880683925920481280110806\",\"topicName\":\"CHECK_ERROR_WITHDRAW_TOPIC\",\"transAmount\":750,\"transTime\":1563433765109,\"trxId\":\"2019070880683925920481280110806\"}";
		MessageBo message = JSONObject.parseObject(json, MessageBo.class);
		System.out.println(JSONObject.toJSONString(message));
	}

}
