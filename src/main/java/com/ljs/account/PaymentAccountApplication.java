package com.ljs.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//支持cron表达式定制
@EnableScheduling
//@EnableBatchProcessing
//自动加载具体哪些包
@SpringBootApplication(scanBasePackages = {"com.ljs.account"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
//启用mybatis自动代理机制
@MapperScan(basePackages="com.ljs.account.dao")
@EnableTransactionManagement
@EnableDiscoveryClient 
public class PaymentAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentAccountApplication.class, args);
	}

}
