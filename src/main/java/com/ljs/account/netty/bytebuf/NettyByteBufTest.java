/**
 * 
 */
package com.ljs.account.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author lijunshi
 *
 */
public class NettyByteBufTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 默认情况下，容量大小是256,最大容量是Integer.MAX_VALUE
		ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(9,100);
		 System.out.println("容量大小是 ： "+byteBuf.capacity()+",最大容量是 ： "+byteBuf.maxCapacity());
		 System.out.println("当前读指针 ： "+byteBuf.readerIndex()+",写指针是 ： "+byteBuf.writerIndex());
		 print(byteBuf);
		 
		 byteBuf.writeBytes(new byte[] {1,2,3,4});
		 print(byteBuf);
		 // 写入一个int型数据，一个int型数据占据4个字节。
		 byteBuf.writeInt(12);
		 print(byteBuf);
		 // 写入字节5后，buf已经满
		 byteBuf.writeBytes(new byte[] {5});
		 print(byteBuf);
		 byteBuf.writeBytes(new byte[] {6});
		 print(byteBuf);
		 System.out.println("getByte(3) return: " + byteBuf.getByte(3));
	     System.out.println("getShort(3) return: " + byteBuf.getShort(3));
	     System.out.println("getInt(3) return: " + byteBuf.getInt(3));
	     print( byteBuf);
	     byteBuf.getByte(1);
	     print( byteBuf);
	}

	private static void print(ByteBuf buffer) {
		System.out.println("capacity(): " + buffer.capacity());
        System.out.println("maxCapacity(): " + buffer.maxCapacity());
        System.out.println("readerIndex(): " + buffer.readerIndex());
        System.out.println("readableBytes(): " + buffer.readableBytes());
        System.out.println("isReadable(): " + buffer.isReadable());
        System.out.println("writerIndex(): " + buffer.writerIndex());
        System.out.println("writableBytes(): " + buffer.writableBytes());
        System.out.println("isWritable(): " + buffer.isWritable());
        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println("===========================");
	}
}
