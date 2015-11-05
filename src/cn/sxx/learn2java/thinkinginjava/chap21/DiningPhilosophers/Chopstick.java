package cn.sxx.learn2java.thinkinginjava.chap21.DiningPhilosophers;

public class Chopstick {
	private boolean taken = false;
	public synchronized void take() throws InterruptedException{
		while(taken)
			wait();
		taken = true;
	}
	
	public synchronized void dorp(){
		taken = false;
		notifyAll();
	}
}
