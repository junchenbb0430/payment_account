package com.ljs.account.desingerpatter.prototype;

public class Student implements Cloneable {

	private String  stuName;
	
	private int  age;
	
	// 对于引用类型，如果想拷贝需要深拷贝
	private Teacher  teacher;
	
	
	
	public Student() {
		super();
	}

	public Student(String stuName, int age, Teacher teacher) {
		super();
		this.stuName = stuName;
		this.age = age;
		this.teacher = teacher;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		 
		Student  stu = (Student)super.clone();
		/**
		 * 深拷贝，说白了就是递归形式的浅拷贝
		 * 对于类A中引用类型或List类型，需要使用递归方式进行拷贝
		 */
		Teacher teacher = (Teacher)stu.getTeacher().clone();
		stu.setTeacher(teacher);
		return stu;
	}
	
	

	@Override
	public String toString() {
		return "Student [stuName=" + stuName + ", age=" + age + ", teacher=" + teacher + "]";
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	
}
