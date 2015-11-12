package cn.sxx.thinkinginjava.chap14.toys;

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class Toy{
	public Toy() {
	}
	
	public Toy(int i) {
	}
}

class FancyToy extends Toy
	implements HasBatteries, Waterproof, Shoots{
	public FancyToy() {
		super(1);
	}
	
}

public class ToyTest {
	static void printInfo(Class cc){
		System.out.println("Class name:" + cc.getName() + "\nis interface? " + cc.isInterface() );
		System.out.println("Simple name: " +cc.getSimpleName() );
		System.out.println("Canonical name: " + cc.getCanonicalName());
	}
	
	public static void main(String[] args) {
		Class c = null;
		try {
			c = Class.forName("cn.sxx.thinkinginjava.chap14.toys.FancyToy");
		} catch (ClassNotFoundException e) {
			System.out.println("can't find FancyToy");
			System.exit(1);
		}
		printInfo(c);
		System.out.println();
		for (Class face : c.getInterfaces()) {
			printInfo(face);
			System.out.println();
		}
		
		Class up = c.getSuperclass();
		Object obj = null;
		try {
			obj = up.newInstance();
		} catch (InstantiationError e) {
			// TODO: handle exception
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printInfo(obj.getClass());
	}
}
