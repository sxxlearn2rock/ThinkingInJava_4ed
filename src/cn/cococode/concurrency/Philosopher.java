package cn.cococode.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
	private Chopstick leftChop;
	private Chopstick rightChop;
	private final int id;
	private final int poderFactor;
	private Random rand = new Random(47);
	
	public Philosopher(Chopstick r, Chopstick l, int id, int pf) {
		this.leftChop = l;
		this.rightChop = r;
		this.id = id;
		this.poderFactor = pf;
	}
	
	private void pause() throws InterruptedException{
		if (poderFactor == 0) {
			return;
		}
		
		TimeUnit.MICROSECONDS.sleep(
				rand.nextInt(poderFactor * 25));
	}

	@Override
	public void run() {
		while(! Thread.interrupted()){
			try {
				System.out.println(this + " " + " thinking");
				pause();
				System.out.println(this + " grabbing right");
				rightChop.take();
				System.out.println(this + " grabbing left");
				leftChop.take();
				System.out.println(this + " eating");
				pause();
				rightChop.drop();
				leftChop.drop();
			} catch (InterruptedException e) {
				System.out.println(this+ " Interrupted");
			}
			
		}
	}
	
	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}
