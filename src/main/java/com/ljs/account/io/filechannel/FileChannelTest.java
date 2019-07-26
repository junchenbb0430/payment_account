package com.ljs.account.io.filechannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class FileChannelTest {

	public static void main(String[] args) {
		char[] chars = new char[] {'A','B','C','D','E'};
		CharBuffer charBuffer = CharBuffer.wrap(chars);
		
		
		/**
		 * getPath中第一个参数是工作区间内的相对路径
		 */
		Path path = FileSystems.getDefault().getPath("log", new String[]{"sql",
								"2019-05-05.Reconciliation_Sql.log"});
		try {
			FileChannel fileChannel = FileChannel.open(path, new StandardOpenOption[]{StandardOpenOption.READ});
			ByteBuffer buffer  = ByteBuffer.allocate(1024);
			int size = fileChannel.read(buffer);
			while(size>0) {
				buffer.flip();
				int reming = buffer.remaining();
				byte[] content = new byte[reming];
				 buffer.get(content) ;
				 
				 System.out.println(new String(content));
				 buffer.flip();
				size = fileChannel.read(buffer);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}

}
