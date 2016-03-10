package cn.cococode.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car{
	 boolean waxOn = false;
	public synchronized void waxed(){
		waxOn = true;
		notify();
	}
	
	public synchronized void buffed(){
		waxOn = false;
		notifyAll();
	}
	
	public synchronized void waitForWaxing() throws InterruptedException{
		while (waxOn == false) {
			wait();
		}
	}
	
	public synchronized void waitForBuffed() throws InterruptedException{
		while (waxOn == true) {
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
		try{
			while(! Thread.interrupted()){
				System.out.println("Wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				synchronized (car) {
					while(car.waxOn == true){ car.wait();}
				}
				
				//car.waitForBuffed();
			}
		}catch (InterruptedException e){
			System.out.println("Interrupted!");
		}
	System.out.println("Ending wax off task");
	}
}

class WaxOff implements Runnable{
	private Car car;
	public WaxOff(Car car) {
		this.car = car;
	}
	
	@Override
	public void run() {
		try{
			while(! Thread.interrupted()){
				System.out.println("Wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
				car.waitForWaxing();
			}
		}catch (InterruptedException e){
			System.out.println("Interrupted!");
		}
	System.out.println("Ending wax on task");
	}
	
}

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newFixedThreadPool(2);
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}
}
