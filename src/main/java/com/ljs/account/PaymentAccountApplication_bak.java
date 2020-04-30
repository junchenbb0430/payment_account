/**
 * 
 */
package com.ljs.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lijunshi
 *
 */
//自动加载具体哪些包
//@SpringBootApplication(scanBasePackages = {"com.ljs.account"})
//@EnableAspectJAutoProxy(proxyTargetClass = true)
////启用mybatis自动代理机制
//@MapperScan(basePackages="com.ljs.account.dao")
//@EnableTransactionManagement
//@EnableDiscoveryClient 
public class PaymentAccountApplication_bak extends SpringBootServletInitializer {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PaymentAccountApplication_bak.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(PaymentAccountApplication_bak.class, args);
	}
}
