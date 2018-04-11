package com.book.web.test;

/**
 * 父类
 * @author liweihan
 *
 */
public class Parent {
	
	public String normalStr = "父类非静态属性";
	public static String staticStr = "父类静态属性";
	public String changeStr = "父类-变量";
	public static String changeStaticStr = "父类-变量-静态";
	
	public void normalMethod() {
		System.out.println("父类非静态方法!");
	}
	
	public static void staticMethod() {
		System.out.println("父类静态方法！");
	}
	
	public void changeMethod() {
		changeStr = "改变父类的非静态属性";
		changeStaticStr = "改变父类的静态属性";
	}

}
