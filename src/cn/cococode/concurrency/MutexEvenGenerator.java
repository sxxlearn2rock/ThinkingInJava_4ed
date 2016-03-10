package cn.cococode.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexEvenGenerator extends IntGenerator{
	private int mNum = 0;
	private Lock lock = new ReentrantLock();
	@Override
	public int next() {
		lock.lock();
		try {
			++mNum;
			Thread.yield();
			++mNum;
			return mNum;
		} finally {
			lock.unlock();
		}
		
	}
}
