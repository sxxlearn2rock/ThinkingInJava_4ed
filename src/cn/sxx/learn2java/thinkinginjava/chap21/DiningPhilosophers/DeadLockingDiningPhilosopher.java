package cn.sxx.learn2java.thinkinginjava.chap21.DiningPhilosophers;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadLockingDiningPhilosopher {
	public static void main(String[] args) throws InterruptedException, IOException {
		int ponder = 5;
		if (args.length > 0) {
			ponder = Integer.parseInt(args[0]);
		int size = 5;			
		}
		
		int size = 5;
		if (args.length > 1) {
			size = Integer.parseInt(args[1]);
		}
		
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] chopsticks = new Chopstick[size];
		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new Chopstick();
		}
		
		for (int i = 0; i < size; i++){
			exec.execute(new Philosopher(chopsticks[i], chopsticks[(i+1) % size], i, ponder));			
		}
		
		if (args.length == 3 && args[2].equals("timeout")){
			TimeUnit.SECONDS.sleep(5);
		}else {
			System.out.println("print 'enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}

}
