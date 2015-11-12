package cn.sxx.thinkinginjava.chap21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestingBlockingQueues {
	static void getkey(){
		try {
			new BufferedReader(
					new InputStreamReader(System.in)).readLine();
		} catch (java.io.IOException e) {
			throw new RuntimeException();
		}
	}
	
	static void getkey(String message){
		System.out.println(message);
		getkey();
	}
	
	static void test(String message, BlockingQueue<LiftOff> queue){
		System.out.println(message);
		LiftOffRunner runner = new LiftOffRunner(queue);
		
		Thread t = new Thread(runner);
		t.start();
		
		for (int i = 0; i < 5; ++i){
			runner.add(new LiftOff(5));
		}
		
		getkey("Press 'Enter' +( " + message + " )");
		t.interrupt();
		System.out.println("Finished " + message + " test");
	}

	public static void main(String[] args) {
		test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
//		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
//		test("SynchronizedQueue", new SynchronousQueue<LiftOff>());
	}
}

class LiftOffRunner implements Runnable{
	private BlockingQueue<LiftOff> rockets;
	
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}
	
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			System.out.println("Interrupted during put()");
		}
	}

	@Override
	public void run() {
		try {
			while (! Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			System.out.println("Walking from take()");
		}
		System.out.println("Exiting LiftOffRanner");
	}	
}