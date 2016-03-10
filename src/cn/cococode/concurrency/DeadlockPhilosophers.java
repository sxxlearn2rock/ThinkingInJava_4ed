package cn.cococode.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadlockPhilosophers {
	public static void main(String[] args) throws InterruptedException {
		int poder = 1;
		int size = 5;
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; ++i) sticks[i] = new Chopstick();
		ExecutorService exec = Executors.newFixedThreadPool(size);
		for (int i = 0; i < size; ++i) {
			exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % 5], i, poder));
		}
		
		//TimeUnit.SECONDS.sleep(55);
		while(true);
		//exec.shutdownNow();
	}
}
