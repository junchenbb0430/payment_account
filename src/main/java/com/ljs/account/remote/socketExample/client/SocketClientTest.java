/**
 * 
 */
package com.ljs.account.remote.socketExample.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author lijunshi
 *
 */
public class SocketClientTest {

	/**
	* @param args
	*/
	public static void main(String[] args) {
		 try {
			Socket socket = new Socket("127.0.0.1",8888);
			boolean connectResult = socket.isConnected();
			System.out.println("connectResult is : "+connectResult);
			OutputStream os = socket.getOutputStream();
			os.write("你好，第一次通过socket发送数据!".getBytes());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
