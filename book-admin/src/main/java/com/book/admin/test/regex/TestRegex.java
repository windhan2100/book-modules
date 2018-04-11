package com.book.admin.test.regex;

import java.util.regex.Pattern;

/**
 * test regex
 * @author liweihan
 *
 */
public class TestRegex {

	public static void main(String[] args) {
		
		/**
		 * http://www.cnblogs.com/sparkbj/articles/6207103.html
		 */
		boolean b = Pattern.matches("a*b", "aaaaab");
		System.out.println(b);
		
	}
}
