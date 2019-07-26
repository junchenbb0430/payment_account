/**
 * 
 */
package com.ljs.account.remote.nio.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

 

/**
 * @author lijunshi
 *
 */
public class TimeClientHandle implements Runnable {

	private String host;
	
	private int port;
	
	private Selector selector;
	
	private SocketChannel socketChannel;
	
	private volatile boolean stop;
	
	
	
	public TimeClientHandle(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		try {
			// 创建SocketChannel实例
			socketChannel = SocketChannel.open();
			// 设置TCP参数，非阻塞
			socketChannel.configureBlocking(false);
			// 创建多路复用器并启动
			selector = Selector.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void run() {
		// 1. 检查是否连接Server成功
		 try {
			this.doConnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			while(!stop) {
				try {
					selector.select(1000);
					 Set<SelectionKey> set = selector.selectedKeys();
					 Iterator<SelectionKey> it = set.iterator();
					 SelectionKey key = null;
					 while(it.hasNext()) {
						 key = it.next();
						 it.remove();
						 this.handleInput(key);
					 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//关闭多路复用器后，注册在Selector上的channel等资源会自动关闭。
		if(selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void doConnect() throws IOException {
		Boolean connectResult = socketChannel.connect(new InetSocketAddress(host,port));
		if(false == connectResult) {
			while(!socketChannel.finishConnect()) {
				 
				System.out.println("****一直在尝试连接***");
			}
		}
		System.out.println("****连接成功***");
			// 连接成功，往多路复用器Selector上注册OP_READ事件，同时发送消息至Server
			if(connectResult) {
				socketChannel.register(selector, SelectionKey.OP_READ);
				this.doWrite(socketChannel);
			}else {
			// 	注册连接事件
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
			}
		 
	}
	
	private void handleInput(SelectionKey key) throws IOException {
		// 只要在Selector上注册某个通道，即是valid
		if(key.isValid()) {
			// 判断channel是否已经连接，如果已经连接，则注册OP_READ事件
			SocketChannel channel = (SocketChannel)key.channel();
			if(channel.isConnected()) {
				if(channel.finishConnect()) {
					socketChannel.register(selector,SelectionKey.OP_READ);
					this.doWrite(channel);
				}
			} 
			
			if(key.isReadable()) {
				ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
				int readBytes = channel.read(byteBuffer);
				if(readBytes>0) {
					byteBuffer.flip();
					byte[] bytes = new byte[byteBuffer.remaining()];
					byteBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("Now is "+body);
					this.stop = true;
				}
			}
		}
	}
	
	private void doWrite(SocketChannel socketChannel) throws IOException {
		byte[] bytes = "QUERY TIME ORDER".getBytes("UTF-8");
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		socketChannel.write(writeBuffer);
		if(!writeBuffer.hasRemaining()) {
			System.out.println("Send orde 2 Server succeed!");
		}
	}

}
