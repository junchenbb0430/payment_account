package com.ljs.account;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ljs.account.common.utils.DateUtil;
import com.ljs.account.concurrent.ScheduledThreadPoolServiceCommponent;
import com.ljs.account.context.ApplicationContextUtil;
import com.ljs.account.service.PullTaskWithDelay;
import com.ljs.account.service.PullTaskWithFixedRate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentAccountApplicationTests {



	 @Autowired
	    private WebApplicationContext context;

	    private MockMvc mvc;


	    @Before
	    public void setup() {
	        mvc = MockMvcBuilders
	                .webAppContextSetup(context)
	                .build();
	    }
	    
	    /**
	     	* 请求数据为json格式
	     * @param data
	     * @param path
	     * @return
	     * @throws Exception
	     */
	    public String postForRest(String data,String path) throws Exception {
	    	 
	    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(path);
	    	builder.content(data);
	    	MvcResult mvcResult = mvc.perform(builder.contentType("application/json")
	    			.characterEncoding("UTF-8")).andReturn();
	    	String result = mvcResult.getResponse().getContentAsString();
	    	return result;
	    } 
	    
	    /**
 	* 普通的请求格式
 * @param reqMap
 * @param path
 * @return
 * @throws Exception
 */
public String plainPost(Map<String,String> reqMap,String path) throws Exception {
	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(path);
	 
	for(Entry<String,String> entry:reqMap.entrySet()) {
		builder.param(entry.getKey(), entry.getValue());
	}
	MvcResult mvcResult = mvc.perform(builder.accept("text/plain")
			.characterEncoding("UTF-8")).andReturn();
	String result = mvcResult.getResponse().getContentAsString();
	return result;
}
	    
	
	
	@Test
	public void testScheduledWithDealy() {
		System.out.println("上下文---"+ApplicationContextUtil.getApplicationContext());
		ScheduledThreadPoolServiceCommponent scheduledService = ApplicationContextUtil.getBeanByClass(ScheduledThreadPoolServiceCommponent.class);
		
		System.out.println("............");
		PullTaskWithDelay delayTask = new PullTaskWithDelay();
		scheduledService.executeTaskWithFixedDelay(delayTask);
		try {
			Thread.sleep(Integer.MAX_VALUE);
			System.out.println("server ended!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testScheduledWithFixedRate() {
		System.out.println("上下文---"+ApplicationContextUtil.getApplicationContext());
		ScheduledThreadPoolServiceCommponent scheduledService = ApplicationContextUtil.getBeanByClass(ScheduledThreadPoolServiceCommponent.class);
	 
		System.out.println("............"+DateUtil.formateDateStr(new Date(),DateUtil.FULL_DATETIME_PATTERN));
		
		 
			PullTaskWithFixedRate delayTask = new PullTaskWithFixedRate();
			scheduledService.executeTaskWithFixedRate(delayTask);
		 
		try {
			Thread.sleep(Integer.MAX_VALUE);
			System.out.println("server ended!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
