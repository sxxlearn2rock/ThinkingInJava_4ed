package cn.sxx.thinkinginjava.chap14;

interface Interface{
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface{

	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("SomethinElse: " + arg);
	}
}

class SimpleProxy implements Interface{

	private Interface proxied;
	
	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}
	
	@Override
	public void doSomething() {
		System.out.println("SimpleProxy doSomething");
		proxied.doSomething();
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("SimpleProxy somethingElse");
		proxied.somethingElse(arg);
	}
	
}

public class SimpleProxyDemo {
	public static void consumer(Interface iface){
		iface.doSomething();
		iface.somethingElse("bonobo");
	}
	
	public static void main(String[] args) {
		consumer(new RealObject());
		System.out.println();
		consumer(new SimpleProxy(new RealObject()));
	}
}
