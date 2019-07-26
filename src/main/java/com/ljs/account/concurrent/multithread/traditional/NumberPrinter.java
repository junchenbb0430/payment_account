/**
 * 
 */
package com.ljs.account.concurrent.multithread.traditional;

/**
 * @author lijunshi
 *	打印数字功能
 */
public class NumberPrinter {

	private  int  number;
	
	
	
	public NumberPrinter() {
		this.number = 0;
	}

	public void print(String threadName) {
		++number;
		this.setNumber(number);
		System.out.println(threadName+"--->"+number);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
