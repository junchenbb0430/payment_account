/**
 * 
 */
package com.ljs.account.netty.client.handler;

import com.ljs.account.netty.code.PacketEnAndDecode;
import com.ljs.account.netty.data.DataPacket;
import com.ljs.account.netty.data.login.LoginRequestPacket;
import com.ljs.account.netty.data.login.LoginResponseDataPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lijunshi
 *  客户单处理业务Handler
 */
public class WeichartClientHandler extends ChannelInboundHandlerAdapter {
	
	/**
	 * client与远程server连接成功后，channelActive就可以进行读写
	 */
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
         // 1. 构建登录请求对象
		System.out.println("向客户端发送登录请求");
		LoginRequestPacket  loginPacket = new LoginRequestPacket();
		loginPacket.setUserId("1111111111");
		loginPacket.setUserName("张三");
		loginPacket.setPassword("55555555");
		// 2. 对象转为---->ByteBuf
		ByteBuf byteBuf = PacketEnAndDecode.encode(loginPacket);
		// 3. 发送数据报文
		ctx.channel().writeAndFlush(byteBuf);
    }
	
	 @Override
	 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	     // 1. 获取ByteBuf    
		 ByteBuf byteBuf = (ByteBuf)msg;
		 // 2.解码，byteBuf--->Packet
		 DataPacket packet = PacketEnAndDecode.decode(byteBuf);
		 if(packet instanceof LoginResponseDataPacket) {
			 LoginResponseDataPacket respPacket = (LoginResponseDataPacket)packet;
			 System.out.println("接收到服务端应答报文如下 ： "+respPacket.toString());
		 }
	 }
}
