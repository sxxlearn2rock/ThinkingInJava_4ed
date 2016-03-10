package cn.cococode.concurrency;

public class EvenGenrator extends IntGenerator {
	private int currentEvenValue = 0;
	
	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}
	
	
}
