package com.ljs.account.desingerpatter.prototype;

/***
 * 原型模式：
 * @author lijunshi
 *
 */
public class PrototypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Teacher teacher = new Teacher();
		teacher.setAge(35);
		teacher.setTeacherName("数学老师");
		
		Student stu = new Student("张三",20,teacher);
		
		Student stu2 = (Student)stu.clone();
		stu2.setStuName("李四");
		stu2.getTeacher().setTeacherName("英语老师");
		System.out.println(stu.toString());
		System.out.println(stu2.toString());
	}

}
