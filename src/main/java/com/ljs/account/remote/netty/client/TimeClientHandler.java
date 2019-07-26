/**
 * 
 */
package com.ljs.account.remote.netty.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author lijunshi
 *  负责消息事件读取和发送，继承ChannelInboundHandlerAdapter，覆盖其中的接收消息和发送消息方法即可
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter  {

	private final static  Logger logger  = LoggerFactory.getLogger(TimeClientHandler.class);

	private   ByteBuf firstMessage;
	
	/**
	 * 当channel中有事件(接收到msg)时，触发channelRead方法读取 ,
	 */
	@Override
	 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	       	  // 1. 接收到server发送的消息转化为ByteBuf
	    	   ByteBuf byteBuf = (ByteBuf)msg;
	    	   // 2. 判断当前byteBuf中可读消息大小
		        int length = byteBuf.readableBytes();
		        // 3. 将ByteBuf中的消息读入字节数据
		        byte[] datas = new byte[length];
		        byteBuf.readBytes(datas);
		        // 4. 将字节数据消息转化为字符串
		        String content = new String(datas,"UTF-8");
		        logger.info("接收到服务端应答消息:{}",content);
	        
	}
	/**
	 * 客户端和server建立成功后，netty的NIO线程会调用channelActive方法，发送消息至server端
	 */
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //
		byte[] req = "first message send server!".getBytes();
		// 1. 创建ByteBuf对象，官方建议使用Unpooled.buffer()创建ByteBuf
		firstMessage = Unpooled.buffer(req.length);
		// 2. 往字节数据组中写入数据
		firstMessage.writeBytes(req);
		// 3. 发送字节数组内容
		ctx.writeAndFlush(firstMessage);
    }
	
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
		 logger.info("unexcepted error {} occur...", cause.getMessage());
		ctx.close();
	}
}
