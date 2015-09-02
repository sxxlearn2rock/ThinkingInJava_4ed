package cn.sxx.learn2java.thinkinginjava.chap21;

import java.io.IOException;

public class ResponsiveUI extends Thread {
	private static volatile 	double d =1;
	public ResponsiveUI() {
		setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		while (true) {
			d = d + (Math.PI + Math.E) / d;
		}
	}
	
	public static void main(String[] args) throws IOException {
//		new UnresponsiveUI();
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);
	}
}

class UnresponsiveUI{
	private volatile double d = 1;
	public UnresponsiveUI() throws IOException {
		while (d > 1) {
			d = d + (Math.PI + Math.E) / d;
		}
		//Never gets here
		System.in.read();
	}
}