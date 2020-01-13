package com.ljs.account.lambda.function;

public class LambdaFunctionInterfaceTest {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				 System.out.println("传统使用匿名内部类方式--成功");
			}
		});
		t1.start();
		Thread  t2 = new Thread(()->{
			System.out.println("函数式表达式使用方式成功");
		});
		t2.start();
	}

}
