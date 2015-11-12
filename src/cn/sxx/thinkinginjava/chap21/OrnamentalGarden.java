package cn.sxx.thinkinginjava.chap21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrnamentalGarden {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; ++i){
			exec.execute(new Entrance(i));
		}
		TimeUnit.SECONDS.sleep(2);
		Entrance.cancel();
		exec.shutdown();
		
		if (! exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("Some task were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}

class Count{
	private int count = 0;
	private Random rand = new Random(47);
	//Remove the synchronized to see counting fail
	public synchronized int increment(){
		int temp = count;
		//��û�� synchronized ,�������д�������ʧ��,total��sum�������Ա��
		if (rand.nextBoolean()) {
			Thread.yield();
		}
		return ( count = ++temp);
	}
	public synchronized int value() { return count; }
}

class Entrance implements Runnable{
	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private int number = 0;	
	private final int id;
	private static volatile boolean canceled = false;
	
	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}
	
	public static void cancel() { canceled = true; }
	
	@Override
	public void run() {
		while (! canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + "   Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted.");
			}
		}
		System.out.println("Stopping " + this);
	}
	
	public synchronized int getValue(){ return number; }
	
	@Override
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}
	
	public static int getTotalCount(){
		return count.value();
	}
	
	public static int sumEntrances(){
		int sum = 0;
		for (Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}
	
}













