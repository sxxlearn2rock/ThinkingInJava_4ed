package cn.sxx.thinkinginjava.chap14;

import java.util.Random;

class Initable{
	static final int staticFinal = 1;
	static final int staticFinal2 = ClassInitialization.rand.nextInt(100);
	static {
		System.out.println("Initializing Initable");
	}
}

class Initable2{
	static int staticNonFinal = 2;
	static {
		System.out.println("Initializing Initable2");
	}
}

class Initable3{
	static int statiNonFinal = 3;
	static {
		System.out.println("Initializing Initable3");
	}
}

public class ClassInitialization {
	public static Random rand = new Random(47);
	public static void main(String[] args) throws Exception {
		Class<Initable> initable = Initable.class;
		System.out.println("After creating Initable ref");
		System.out.println(Initable.staticFinal);			//
		System.out.println(Initable.staticFinal2);
		
		System.out.println(Initable2.staticNonFinal);
		Class<?> initable3 = Class.forName("cn.sxx.thinkinginjava.chap14.Initable3");
		System.out.println("After creating Initable3 ref");
		System.out.println(Initable3.statiNonFinal);
	}
}
