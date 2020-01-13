package com.ljs.account.lambda.function;

public class LambdaFunctionArgumentTest {

	public static void main(String[] args) {
		 
		LambdaFunctionArgumentTest test = new LambdaFunctionArgumentTest();
		
		String result2 = test.invoke(new LambdaFunctionArgumentInterface() {
			@Override
			public String invoker(String name) {
				return  "伟大的"+name;
			}	
		},"成吉思汗");
		System.out.println(result2);
		//  lambda表达式与上述采用匿名内部类方式一样，只是代码更优雅
		String  result = test.invoke((fullName)->{
			return "伟大的"+fullName;
		},"成绩思汗");
		System.out.println(result);
		
	}

	
	
	
	public String  invoke(LambdaFunctionArgumentInterface function,String name ) {
		 
		return  "welcome  "+function.invoker(name);
	}
}
