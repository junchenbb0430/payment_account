/**
 * 
 */
package com.ljs.account.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

/**
 * @author lijunshi
 *
 */
public class OptionalTest {

	@Test
	public void testOptional() throws ClassNotFoundException {
		ClassLoader classLoader = Class.forName("com.ljs.account.ratelimit.AccountRateLimiterComponent").getClassLoader();
		classLoader.loadClass("com.ljs.account.ratelimit.AccountRateLimiterComponent");
		List<String> arry = new ArrayList<String>(3);
		arry.add("1");
		arry.add("2");
		arry.add("3");
		arry.add("4");
		arry.add("5");
		System.out.println("arry size  is "+arry.size());
		
		String strs = "http://kohala-paycore.mirror.ehomepay.local/order/?/channel";
		strs = strs.replace("?","LKL-ALIPAY");
		System.out.println(strs);
		Map<String,String> map  = new HashMap<String,String>();
		map.put("111", "222222");
		 List<String> arrList = new ArrayList<String>();
		 arrList.add("A");
		 arrList.add("B");
		 arrList.add("C");
		 arrList = null;
		 Optional<List<String>> listOpt= Optional.ofNullable(arrList);
		 arrList = listOpt.orElse(new ArrayList<String>());
		 arrList.add("D");
		 for(String str:arrList) {
			 System.out.println(str);
		 }
	}
}
