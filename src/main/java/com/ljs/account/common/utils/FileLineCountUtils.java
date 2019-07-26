/**
 * 
 */
package com.ljs.account.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lijunshi
 *
 */
public class FileLineCountUtils {

	/**
	* @param args
	*/
	public static void main(String[] args) {
		FileLineCountUtils flc = new FileLineCountUtils();
		 
		 
		 String filePath = "E:\\workspace\\billingexecution\\src\\main\\java\\com\\ehomepay\\billingexecution";
		 long fileCount = flc.statisticFileCounter(filePath); 
		 System.out.println("文件总数是 ： "+fileCount);
		 String filePath2 = "E:\\workspace\\billingconfiguration\\src\\main\\java\\com\\ehomepay\\billingconfiguration";
		 long fileCount2 = flc.statisticFileCounter(filePath2);
		 System.out.println(fileCount+fileCount2);
	}

	/**
	 * 基于递归方式统计某一个目录下文件中有多少行代码
	* @param filePath
	* @return
	 */
	public long  statisticFileCounter(String filePath) {
		long fileCount = 0;
		File file = new File(filePath);
		if(file.isFile()) {
			fileCount = this.statisticFileLine(file);
			String fileName = file.getAbsolutePath();
			System.out.println("当前文件--->"+fileName);
			
		}else if(file.isDirectory()) {
			File[] files =file.listFiles();
			for(File f:files) {
				long count = statisticFileCounter(f.getAbsolutePath());
				fileCount +=count;
			}
		}
		return fileCount;
	}
	
	
	public long statisticFileLine(File file) {
		long counter = 0 ;
		FileInputStream fis = null;
		BufferedReader bufferReader  = null;
		 try {
			 //没有释放资源
		    fis = new FileInputStream(file);
			bufferReader = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
			String content = null;
			while((content = bufferReader.readLine())!=null) {
				if(StringUtils.isBlank(content)) {
					continue;
				}
				counter = counter+1;
			}
		} catch (Exception e) {
			 
			e.printStackTrace();
		} finally {
			if(null != bufferReader) {
				try {
					bufferReader.close();
				} catch (IOException e) {
				 
					e.printStackTrace();
				}
			}
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		 return counter;
	}
	
}
