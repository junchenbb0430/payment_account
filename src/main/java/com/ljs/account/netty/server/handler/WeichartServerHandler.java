/**
 * 
 */
package com.ljs.account.netty.server.handler;

import com.ljs.account.netty.code.PacketEnAndDecode;
import com.ljs.account.netty.data.DataPacket;
import com.ljs.account.netty.data.login.LoginRequestPacket;
import com.ljs.account.netty.data.login.LoginResponseDataPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lijunshi
 *
 */
public class WeichartServerHandler extends ChannelInboundHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 1. 转义为ByteBuf
		ByteBuf  byteBuf = (ByteBuf)msg;
        // 2.将ByteBuf转为java对象
		DataPacket dataPacket = PacketEnAndDecode.decode(byteBuf);
		LoginRequestPacket loginPacket = (LoginRequestPacket)dataPacket;
		System.out.println("服务端收到客户单请求报文 ： "+loginPacket.toString());
		// 3.发送应答报文
		// 3.1 创建应答报文对象
		LoginResponseDataPacket response = new LoginResponseDataPacket();
		response.setStatus("SUCCESS");
		response.setUserId(loginPacket.getUserId());
		response.setUserName(loginPacket.getUserName());
		// 3.2 将报文对象转为字节流
		ByteBuf respByteBuf = PacketEnAndDecode.encode(response);
		ctx.channel().writeAndFlush(respByteBuf);
		System.out.println("服务端发送响应消息完毕!");
		 
    }
}
