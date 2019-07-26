/**
 * 
 */
package com.ljs.account.remote.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lijunshi
 *
 */
public class NettyTimeClient {

	public void start() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			 Bootstrap boot = new   Bootstrap();
			 boot.group(group).channel(NioSocketChannel.class)
			 .option(ChannelOption .TCP_NODELAY,true)
			 // 处理请求servr request
			 .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					 ch.pipeline().addLast(new TimeClientHandler());
				}});
			  // 发起异步连接
			 ChannelFuture future =  boot.connect("127.0.0.1",9999).sync();
			 // 等待客户端链路关闭连接
			 future.channel().closeFuture().sync();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
	}
	
	public  static  void main(String[] args) {
		NettyTimeClient discard = new NettyTimeClient();
		discard.start();
	}
}
