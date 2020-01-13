/**
 * 
 */
package com.ljs.account.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author lijunshi
 *  ByteBuf三种模式：
 *  	1. 堆内模式
 *  	2. 直接缓冲区，对外模式
 *  	3. 复合缓冲区，
 */
public class ByteBufThreeModeTest {
	
	public static void main(String[] args) {
		System.out.println(20%15);
		System.out.println(20/15);
		byte[] as = new byte[6];
		for(int i=0;i<6;i++) {
			as[i]= (byte)(6+i);
		}
		for(int i=0;i<6;i++) {
			System.out.println(as[i]);
		}
		ByteBufThreeModeTest byteBufTest = new ByteBufThreeModeTest();
		
		byteBufTest.compositeByteBuf();
	}
	

	public void compositeByteBuf() {
		CompositeByteBuf compositeBuf = Unpooled.compositeBuffer();
		ByteBuf heapBuffer = ByteBufAllocator.DEFAULT.heapBuffer(12);
		if(heapBuffer.hasArray()) {
			System.out.println("heapBuffer() 创建 堆内缓冲区");
		}
		ByteBuf directBuffer = ByteBufAllocator.DEFAULT.directBuffer(10);
		if(!directBuffer.hasArray()) {
			System.out.println("directBuffer() 创建 直接缓冲区");
		}
		ByteBuf ioBuffer = ByteBufAllocator.DEFAULT.ioBuffer(14);
		if(!ioBuffer.hasArray()) {
			System.out.println("ioBuffer() 创建 直接缓冲区");
		}
		
		compositeBuf.addComponents(heapBuffer,directBuffer,ioBuffer);
		int length = compositeBuf.readableBytes();
		System.out.println("可读字节数是 ： "+length);
		for(ByteBuf byteBuf:compositeBuf) {
			System.out.println(byteBuf.toString());
		}
		System.out.println("********删除第一个byteBuf*****");
		compositeBuf.removeComponent(0);//删除第一个索引位置的byteBuf
		for(ByteBuf byteBuf:compositeBuf) {
			System.out.println(byteBuf.toString());
		}
	}
}
