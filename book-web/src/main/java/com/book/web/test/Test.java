package com.book.web.test;

/**
 * 测试类
 * @author liweihan
 *
 */
public class Test {
	
	public static void main(String[] args) {
		Child child = new Child();
		System.out.println(child.normalStr);
		System.out.println(child.staticStr);
		child.normalMethod();
		child.staticMethod();
		System.out.println("------------------- 静态属性、静态方法和非静态的属性、非静态方法都可以被 继承");
		
		//父类指向之类实例
		Parent child1 = new Child();
		System.out.println(child1.normalStr);
		System.out.println(child1.staticStr);
		child1.normalMethod();
		child1.staticMethod();
		System.out.println(" ================== 静态属性、静态方法和非静态的属性都可以被 继承 和 隐藏  而不能够被重写");
		
		//获取变量的值
		Parent parent = new Parent();
		System.out.println("1 ... " + parent.changeStr);
		System.out.println("1 --- " + parent.changeStaticStr);
		
		Child child2 = new Child();
		System.out.println("2 ... 子类获取父类非静态属性值： " + child2.changeStr);
		System.out.println("2 --- 子类获取父类静态属性值： " + child2.changeStaticStr);
		
		parent.changeMethod();
		System.out.println("3 ... 子类获取父类改变后非静态属性值 ：" + child2.changeStr);
		System.out.println("4 ... 子类获取父类改变的静态属性值：" + child2.changeStaticStr);
		System.out.println(" ------------- 父类中的静态属性值,父类初始化后,子类可以直接使用！但是,非静态属性,不可以这样！");
		
		/**
		 * 结论：
		 一、对于非静态属性和方法：
		 <1.>对于非静态属性，子类可以继承父类的非静态属性。但是，当子类和父类有相同的非静态属性时，
		   并没有重写并覆盖父类的非静态属性,只是隐藏了父类的非静态属性。
		 <2.>对于非静态方法,子类可以继承父类的非静态方法并可以重写覆盖父类的非静态属性方法。
		 
		 二、对于静态的属性和方法
		 <1.>对于静态属性,子类可以继承父类的静态属性。但是和非静态的属性一样，会被隐藏。
		 <2.>对于静态的方法,子类可以继承父类的静态方法。但是，子类不可重写父类的静态方法,
		     子类的同名静态方法会隐藏父类的静态方法。
		
		 三、http://www.cnblogs.com/kabi/p/5181941.html
		 <1.>java中静态属性和静态方法可以被继承,但是没有被重写(overwrite)而是被隐藏。
		 <2.>静态方法和属性是属于类的，调用的时候直接通过类名.方法名完成的，不需继承机制就可以调用。
		 	如果子类里面定义了静态方法和属性，那么这时候父类的静态方法 或属性称之为“隐藏”，
		 	你如果想要调用父类的静态方法和属性，直接通过父类名.方法名或变量名完成，
		 	至于是否继承一说，子类是有继承静态方法和属性，但是 跟实例方法和属性不太一样，存在“隐藏”的这种情况。
		 	
		 <3.>静态属性、静态方法和非静态的属性都可以被 继承 和 隐藏  而不能够被重写，
		              非静态的方法可以被继承和重写，
		 */
		
		
	}

}
