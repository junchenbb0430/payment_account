/**
 * 
 */
package com.ljs.account.netty.code;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.ljs.account.netty.data.Command;
import com.ljs.account.netty.data.DataPacket;
import com.ljs.account.netty.data.login.LoginRequestPacket;
import com.ljs.account.netty.data.login.LoginResponseDataPacket;
import com.ljs.account.netty.serializer.SerializerService;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author lijunshi
 * 将数据包进行编码，DataPacket----->ByteBuf
 *将数据包进行解码，ByteBuf ------> DataPacket
 */
public class PacketEnAndDecode {

	/***
	 * 魔法数
	 */
	private static final int  MAGIC_NUM = 10;
	
	private static   Map<Byte, Class<? extends DataPacket>> packetMap;
	
	static {
		packetMap = new HashMap<Byte, Class<? extends DataPacket>>();
		packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
		packetMap.put(Command.LOGIN_RESPONSE,LoginResponseDataPacket.class);
	}
	
	public static  ByteBuf   encode(DataPacket  packet) {
		// 创建byteBuf对象
		ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
		// 将数据对象进行序列化
		byte[] packetByte = SerializerService.deault.serializer(packet);
		/**
		 *  编码数据报文 ,协议格式 :
		 *  魔法数(4)|版本号(1)|序列化算法(1)|指令(1)|数据长度(4)|数据内容(N)
		 */
		byteBuf.writeInt(MAGIC_NUM); // 魔法数
		byteBuf.writeByte(packet.getVersion());//版本号
		byteBuf.writeByte(SerializerService.deault.getSerializerAlgoritm());//序列化算法
		byteBuf.writeByte(packet.getCommand());//指令
		byteBuf.writeInt(packetByte.length);//数据长度
		byteBuf.writeBytes(packetByte);//数据内容
		return byteBuf;
	}
	/***
	 * 解析报文，根据通讯协议，将数据报文----->DataPacket
	 *数据格式如下：
	 *魔法数(4)|版本号(1)|序列化算法(1)|指令(1)|数据长度(4)|数据内容(N)
	 * @param <T>
	 * @param dataByte
	 * @return
	 */
	public static DataPacket decode(ByteBuf byteBuf) {
		// 跳过魔法数
		byteBuf.skipBytes(4);
		// 跳过版本号
		byteBuf.skipBytes(1);
		// 跳过序列化算法
		byteBuf.skipBytes(1);
		// 读取指令
		byte command = byteBuf.readByte();
		// 根据指令获取Class类型
		Class<? extends DataPacket> classPacket = getRequestPacketType(command);
		/**
		 * 读取数据报文长度,readInt一次读取4个字节，封装了byte-->int转化
		 */
		int length = byteBuf.readInt();
		
		// 读取数据内容
		byte[] data = new byte[length];
		byteBuf.readBytes(data);
		// 反序列化字节对象
		return SerializerService.deault.deserializer(data, classPacket);
		 
	}
	
	static Class<? extends DataPacket>  getRequestPacketType(byte command){
		 return packetMap.get(command);
	}
	
	public static void main(String[] args) {
		LoginRequestPacket  loginPacket = new LoginRequestPacket();
		loginPacket.setUserId("1111111111");
		loginPacket.setUserName("张三");
		loginPacket.setPassword("55555555");
		ByteBuf byteBuf = PacketEnAndDecode.encode(loginPacket);
		System.out.println(byteBuf.toString(Charset.forName("UTF-8")));
		DataPacket packet = PacketEnAndDecode.decode(byteBuf);
		System.out.println(((LoginRequestPacket)packet).toString());
		System.out.println((byte)563);
	}
}
