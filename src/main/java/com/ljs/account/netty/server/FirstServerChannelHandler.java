/**
 * 
 */
package com.ljs.account.netty.server;

import java.nio.charset.Charset;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lijunshi
 *
 */
public class FirstServerChannelHandler extends ChannelInboundHandlerAdapter {
		
		/***
		 * 底层socket.read()读取ByteBuf后，触发channelRead事件，
		 * 由I/O事件线程NioEventLoop调用ChannelPipeline的fireChannelRead(),将message(ByteBUf)
		 * 传输到 ChannnelPipeline，然后ByteBuf一次被HeadHandler，ChannelHandler拦截和处理。
		 */
	  @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	        ByteBuf  byteBuf = (ByteBuf)msg;
	        String message = byteBuf.toString(Charset.forName("UTF-8"));
	        System.out.println(new Date()+"服务端已经读取到数据 : "+message);
	        
	        System.out.println(new Date()+"服务端开始向客户端写数据 !");
	        byte[] toclient = "服务端已收到数据，客户端你好！".getBytes("UTF-8");
	        ByteBuf  serverByteBuf =ctx.alloc().buffer();
	        serverByteBuf.writeBytes(toclient);
	        Channel channel = ctx.channel();
	        System.out.println("channel instance is : "+channel);
	        channel.writeAndFlush(serverByteBuf);
	    }
}
