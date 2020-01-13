/**
 * 
 */
package com.ljs.account.netty.server;

import java.nio.charset.Charset;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lijunshi
 *
 */
public class MessageInboudChannelHandler2 extends ChannelInboundHandlerAdapter {
		
		/***
		 * 底层socket.read()读取ByteBuf后，触发channelRead事件，
		 * 由I/O事件线程NioEventLoop调用ChannelPipeline的fireChannelRead(),将message(ByteBUf)
		 * 传输到 ChannnelPipeline，然后ByteBuf一次被HeadHandler，ChannelHandler拦截和处理。
		 */
	  @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	        ByteBuf  byteBuf = (ByteBuf)msg;
	        String message = byteBuf.toString(Charset.forName("UTF-8"));
	        System.out.println(new Date()+" Server2 read message : "+message);
	    }
}
