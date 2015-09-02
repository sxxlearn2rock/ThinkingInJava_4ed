package cn.sxx.learn2java.thinkinginjava.chap21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn(car));
		exec.execute(new WaxOff(car));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}
}


class Car{
	private boolean waxOn = false;
	
	public synchronized void waxed(){
		waxOn = true;
		notifyAll();
	}
	
	public synchronized void buffed(){
		waxOn = false;
		notifyAll();
	}
	
	public synchronized void waitingForBuff() throws InterruptedException{
		while (waxOn) {
			wait();
		}
	}
	
	public synchronized void waitingForWax() throws InterruptedException{
		while (!waxOn) {
			wait();
		}
	}
}

class WaxOn implements Runnable{
	private Car car;
	
	public WaxOn(Car car) {
		this.car = car;
	}
	
	@Override
	public void run() {
		try {
			while (! Thread.interrupted()){
				System.out.print("Wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitingForBuff();
			}

		} catch (InterruptedException e) {
//			e.printStackTrace();
			System.out.println("Wax on is interrupted!");
		}	
		System.out.println("Ending wax on task");
	}	
}

class WaxOff implements Runnable{
	private Car car;
	
	public WaxOff(Car car) {
		this.car = car;
	}
	
	@Override
	public void run() {
		try {
			while (! Thread.interrupted()){
				System.out.print("Wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
				car.waitingForWax();
			}
			
		} catch (InterruptedException e) {
//			e.printStackTrace();
			System.out.println("Wax off is interrupted!");
		}
		System.out.println("Ending wax off task");
	}
}










