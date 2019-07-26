/**
 * 
 */
package com.ljs.account.remote.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lijunshi
 *
 */
public class MultiplexerTimeServer implements Runnable {

	private Selector selector;
	
	private ServerSocketChannel serverChannel;
	
	private volatile boolean stop=false;
	
	public MultiplexerTimeServer(int port) {
		try {
			//1.创建ServerSocketChannel，用于监听client连接
			serverChannel = ServerSocketChannel.open();
			//2.绑定hostname和port
			serverChannel.bind(new InetSocketAddress("127.0.0.1",port));
			//3. 设置成非阻塞
			serverChannel.configureBlocking(false);
			// 4.创建多路复用器
			  selector = Selector.open();
			// 5.将ServerSocketChannel注册到多路复用器上，监听OP_ACCEPT事件
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is started in port :"+port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while(!stop) {
				 
				// 休眠一秒
				 selector.select(1000);
				 /** 当有处于就绪状态的channel时(服务启动时，注册到复用器Selector上的ServerSocketChannel),
				  *  selector返回Channel的SelectionKey集合。
				  *  每一种SelectionKey代表一种事件。
				  */
				 Set<SelectionKey> selectKeys = selector.selectedKeys();
				 
				 Iterator<SelectionKey> it = selectKeys.iterator();
				 SelectionKey key = null;
				 while(it.hasNext()) {
					 key = it.next();
					 it.remove();
					 // 异步处理网络事件
					 this.handleInput(key);
					 if(key!=null) {
						 key.cancel();
					 }
					 if(key.channel() !=null) {
						 key.channel().close();
					 }
				 }
			 }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		// 多路复用器关闭后，所有注册在selector上 的资源(例如，channel和Pipe)将自动去除注册并且关闭。
		 if(selector !=null) {
			 try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }

	}

	
	private void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()) {
		 //处理新接入请求消息
			if(key.isAcceptable()) {
				// accept the new Connection
				ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
				// 接受客户端的连接请求并创建SocketChannel实例。创建成功，即完成TCP三次握手	
				SocketChannel sc = ssc.accept();
				// 设置SocketChannel的各种参数,设置成非阻塞
				sc.configureBlocking(false);
				// 连接建立完毕后，注册读消息事件，往多路复用器上注册读事件。
				sc.register(selector,SelectionKey.OP_READ);
			}
			
			if(key.isReadable()) {
				// read the data from  buffer
				SocketChannel sc = (SocketChannel)key.channel();
				// 声明缓冲
				ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
				// 将数据从channel读取到buffer中
				int readBytes = sc.read(byteBuffer);
				System.out.println("读取的字节大小 ： "+readBytes);
				if(readBytes>0) {
					byteBuffer.flip();
					byte[] bytes= new byte[byteBuffer.remaining()];
					byteBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("The time server receive order :"+body);
					String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
							new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
					this.doWrite(sc,currentTime);		
				// 没有读取到，对端链路关闭	
				}else if(readBytes<0) {
					key.cancel();
					sc.close();
				} 
			}
		}
	}
	/**
	 * NIO中读入和写出操作的都是缓冲 buffer
	* @param channel
	* @param response
	 * @throws IOException 
	 */
	private void doWrite(SocketChannel channel,String response) throws IOException {
			if(StringUtils.isNotBlank(response)) {
				byte[] bytes = response.getBytes();
				// 声明缓冲区大小
				ByteBuffer buffer = ByteBuffer.allocate(8192);
				// 将字节数组写入buffer中
				buffer.put(bytes);
				// 写完之后，将buffer设置成读模式
				buffer.flip();
				// 将缓冲中的数据写入至channel中
				channel.write(buffer);
				
			}
	}
}
