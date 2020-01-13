/**
 * 
 */
package com.ljs.account.netty.client;

import java.nio.charset.Charset;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lijunshi
 * Netty中主题思想：回调，ChannelFuture和事件驱动
 */
public class FirstClientHanlder extends ChannelInboundHandlerAdapter{

	/**
	 * channelActive表示当前channel是活跃的，可以进行远程读写功能.
	 * 客户端连接成功后，这个channelActive方法会被调用。
	 */
	@Override
	 public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(new Date()+"：客户端写数据");
		
		// 获取二进制抽象ByteBuf
		ByteBuf buffer = ctx.alloc().buffer();
		
		byte[] bytes = "你好,服务端！".getBytes("UTF-8");
		// 往buffer中写字节内容
		buffer.writeBytes(bytes); 
		
		// 返回绑定在ChannelHandlerContext上的Channel
		Channel channel = ctx.channel();
		// 将buffer写入到channel中
		channel.writeAndFlush(buffer);
		super.channelActive(ctx);
	  }
	  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	        ByteBuf serverByteBuf =(ByteBuf)msg;
	        System.out.println("客户端读取到服务端数据  ： "+serverByteBuf.toString(Charset.forName("UTF-8")));
	  }
}
