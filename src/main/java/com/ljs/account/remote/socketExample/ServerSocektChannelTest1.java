/**
 * 
 */
package com.ljs.account.remote.socketExample;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

/**
 * @author lijunshi
 *	
 */
public class ServerSocektChannelTest1 {

	/**
	 * 使用ServerSocketChannel实现ServerSocket阻塞式通信
	 */
	public void startServer() {
		try {
			// 1. 创建ServerChannel
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			// 2. ServerSocketChannel也可以获取ServerSocket，这种方式不能使用I/O多路复用。
			ServerSocket serverSocket = serverSocketChannel.socket();
			System.out.println("ServerSocketChannel is blocking : "+serverSocketChannel.isBlocking());
			/** 3. 绑定监听地址和端口；
			 *  也可以使用serverSocketChannel.bind();与ServerSocket绑定二选一。
			 */
			serverSocket.bind(new InetSocketAddress("127.0.0.1",8888));
			//serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
			// 4. 监听客户端连接
			Socket socket = serverSocket.accept();
			System.out.println("*****client has connected.......");
			// 5. 读取流数据，非NIO模式
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			// 6. 阻塞调用，将socket中流信息读入至byte[]中
			char[] buffer  = new char[2048];
			int  length = inputStreamReader.read(buffer);
			while(length != -1) {
				String newStr = new String(buffer,0,length);
				System.out.println("server receives message : "+newStr);
				length = inputStreamReader.read(buffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
	public void startServerSocketChannelBind() {
		try {
			// 1. 创建ServerChannel
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			// 2. ServerSocketChannel也可以获取ServerSocket，这种方式不能使用I/O多路复用。
			ServerSocket serverSocket = serverSocketChannel.socket();
			System.out.println("ServerSocketChannel is blocking : "+serverSocketChannel.isBlocking());
			/** 3. 绑定监听地址和端口；
			 *  也可以使用serverSocketChannel.bind();与ServerSocket绑定二选一。
			 */
			//serverSocket.bind(new InetSocketAddress("127.0.0.1",8888));
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
			// 4. 监听客户端连接
			Socket socket = serverSocket.accept();
			System.out.println("*****client has connected.......");
			// 5. 读取流数据，非NIO模式
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			// 6. 阻塞调用，将socket中流信息读入至byte[]中
			char[] buffer  = new char[2048];
			int  length = inputStreamReader.read(buffer);
			while(length != -1) {
				String newStr = new String(buffer,0,length);
				System.out.println("server receives message : "+newStr);
				length = inputStreamReader.read(buffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
	/**
	* @param args
	*/
	public static void main(String[] args) {
		ServerSocektChannelTest1 test1 = new ServerSocektChannelTest1();
		test1.startServer();

	}

}
