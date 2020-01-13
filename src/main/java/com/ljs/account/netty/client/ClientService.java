/**
 * 
 */
package com.ljs.account.netty.client;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author lijunshi
 *
 */
public class ClientService implements Runnable {
	public static int MAX_RETRY = 4;
	@Override
	public void run() {

		// 线程池工作组
		NioEventLoopGroup  workerGroup = new NioEventLoopGroup();
		
		Bootstrap bootstrap = new Bootstrap();
		// 设置线程组
		bootstrap.group(workerGroup)
				// 设置I/O类型
				.channel(NioSocketChannel.class)
				// I/O处理逻辑，该channel将被添加到ChannelPipeline中，用来接收事件通知
				.handler(new ClientChannelInitializer())
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY,true);
		
		// 开始连接
		connect(bootstrap,"127.0.0.1",8888,4);
	
	}
	private    void connect(Bootstrap bootstrap,String host,int port,int retry) {
		// 1 .连接远程地址，返回ChannelFuture，异步操作
		ChannelFuture channelFuture = bootstrap.connect(host,port);
		// 异步接收连接通知事件通知
		channelFuture
				.addListener((GenericFutureListener<? extends Future<? super Void>>) 
				 future -> {
					if(future.isSuccess()) {
						System.out.println("client 连接成功");
					}else if(retry==0){
						System.out.println("client connect faliure,重试次数已经用尽");
					}else {
						int count = (MAX_RETRY-retry)+1;
						int delay = 1<<count;
						System.out.println("client 连接失败,进行第 "+count+"次重试!");
						bootstrap.config().group().schedule(()->connect(bootstrap,host,port,retry-1), delay,TimeUnit.SECONDS);
					}
		});
	}
}
