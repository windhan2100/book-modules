package com.book.web.test;


/**
 * 子类继承父类
 * @author liweihan
 *
 */
public class Child extends Parent{
	
	public String normalStr = "子类-非静态属性";
	public static String staticStr = "子类-静态属性";
	
//	@Override
	public void normalMethod() {
		System.out.println("子类-非静态方法!");
	}
	
	/**
	 * 静态方法不能被重写,所以,去掉Override
	 */
//	@Override
	public static void staticMethod() {
		System.out.println("子类-静态方法！");
	}
}
