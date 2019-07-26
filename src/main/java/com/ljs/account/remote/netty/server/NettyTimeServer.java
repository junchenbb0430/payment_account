/**
 * 
 */
package com.ljs.account.remote.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lijunshi
 *
 */
public class NettyTimeServer {
	
	private final static Logger logger = LoggerFactory.getLogger(NettyTimeServer.class);
	private int port;

	public NettyTimeServer(int port) {
		super();
		this.port = port;
	}

	public void run() {
		
			EventLoopGroup bossGroup = new NioEventLoopGroup();
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			try {
				/**
				 *  辅助类，创建Server，
				 *  NioServerSocketChannel创建新的Channel实例,接受新的连接
				 *  ChannelInitializer是一种handler，帮助用户配置一个新的channel
				 */
				ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				/** Set the {@link ChannelHandler} 
				which is used to serve the request for the {@link Channel}'s.
				事件处理器：
				**/
					.childHandler( new TimeServerHandler())
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture cf = b.bind(port).sync();
			logger.info("netty server start success!");
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args) {
		NettyTimeServer  server = new NettyTimeServer(9999);
		server.run();
	}
}
