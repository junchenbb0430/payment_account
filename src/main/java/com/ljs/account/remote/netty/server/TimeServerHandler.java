/**
 * 
 */
package com.ljs.account.remote.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author lijunshi
 *
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	 private final static Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);
	/**
	 * 接收事件处理机制，读取到之后再发送给客户端
	 * msg:表示消息对象类型，ByteBuf或者其他对象类型
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 logger.info("接收到客户端请求消息为={}",msg);
		try {
			// 1. 从buffer中读取数据
			ByteBuf byteBuf = (ByteBuf)msg;
			int capacity = byteBuf.capacity();
			//while(byteBuf.isReadable()) {
				int length =  byteBuf.readableBytes();
				logger.info("接收到byteBuf容量大小是={},bytebuf中消息大小是={}",capacity,length);
			    byte[] datas = new byte[length];
			    byteBuf.readBytes(datas);
			    String content = new String(datas,"UTF-8");
			    System.out.println("server接收到的内容:"+content);
			    content = "服务端已经收到："+content;
			    // 4. 根据指定的字节数byte组创建ByteBuf对象
			    ByteBuf respByte = Unpooled.copiedBuffer(content.getBytes());
			    // 5. 往通道 中写入数据   ctx.writeAndFlush(msg); 等于如下俩方法
			    ctx.write(respByte);
			    ctx.flush();
			   
			//}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
