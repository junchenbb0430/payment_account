/**
 * 
 */
package com.ljs.account.desingerpatter.prototype;

/**
 * @author lijunshi
 *
 */
public class Teacher implements Cloneable {
	
	private String teacherName;
	
	private int age;
	
	
	
	
	 public Teacher() {
		super();
	}


	public Teacher(String teacherName, int age) {
		super();
		this.teacherName = teacherName;
		this.age = age;
	}


	protected   Object clone() throws CloneNotSupportedException{
		 return super.clone();
	 }

	

	@Override
	public String toString() {
		return "Teacher [teacherName=" + teacherName + ", age=" + age + "]";
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	 
	 
}
