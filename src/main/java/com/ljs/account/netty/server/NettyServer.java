/**
 * 
 */
package com.ljs.account.netty.server;

import com.ljs.account.netty.server.handler.InboundHandlerTestA;
import com.ljs.account.netty.server.handler.InboundHandlerTestB;
import com.ljs.account.netty.server.handler.InboundHandlerTestC;
import com.ljs.account.netty.server.handler.OutboundHandlerTestA;
import com.ljs.account.netty.server.handler.OutboundHandlerTestB;
import com.ljs.account.netty.server.handler.OutboundHandlerTestC;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author lijunshi
 *
 */
public class NettyServer {

	public static void main(String[] args) {
		// 创建BOSS线程组
		NioEventLoopGroup group = new NioEventLoopGroup();
		// 创建worker线程组
		NioEventLoopGroup worker = new NioEventLoopGroup();
		// 创建引导启动类
		ServerBootstrap  bootstrap = new ServerBootstrap();
		bootstrap.group(group, worker) //设置线程组，BOSS和worker
				.channel(NioServerSocketChannel.class) //指定I/O类型 ，处理请求
				// 服务启动中，处理逻辑，一般使用不到。
				.handler(new ChannelInitializer<NioServerSocketChannel>() {
					@Override
					protected void initChannel(NioServerSocketChannel ch) throws Exception {
						 System.out.println(" BOSS server starting  !");
					}
				})
				.option(ChannelOption.SO_BACKLOG, 1024)// Server端请求队列深度
				.childOption(ChannelOption.SO_KEEPALIVE,true)//开启底层TCP心跳机制
				.childOption(ChannelOption.TCP_NODELAY,true)//要求高实时性，关闭；减少发送次数和网络交互true
				// option和childOption可以设置一些底层TCP相关属性
				//设置后续每条连接的数据读写，每个连接成功的客户端
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) throws Exception {
						 System.out.println("Server has received cliet request!");
						 ChannelPipeline pipeline = ch.pipeline();
						 // 聊天handler
						 //pipeline.addLast(new WeichartServerHandler());
						 // pipeline.addLast(new FirstServerChannelHandler());
						 /************************************************************/
						 // inboundHandler与outboundHandler测试
//						 pipeline.addLast(new InboundHandlerTestA());
//						 pipeline.addLast(new InboundHandlerTestB());
//						 pipeline.addLast(new InboundHandlerTestC());
						 pipeline.addLast(new OutboundHandlerTestA());
						 pipeline.addLast(new OutboundHandlerTestB());
						 pipeline.addLast(new OutboundHandlerTestC());
						 //ch.pipeline().addLast(new MessageInboudChannelHandler2());
					}
				});
				
		
		ChannelFuture channelFuture = bootstrap.bind("127.0.0.1",8888);
		// 设置监听器,netty中所有操作都是异步
		channelFuture.addListener(new GenericFutureListener< Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) throws Exception {
				 if(future.isSuccess()) {
					 System.out.println("server bind port success!");
				 }else {
					 System.out.println("Server bind port failure!");
				 }
			}
			
		});
	}
}
