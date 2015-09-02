package cn.sxx.learn2java.thinkinginjava.chap21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {
	Meal meal;
	Chef chef = new Chef(this);
	Waiter waiter = new Waiter(this);
	
	ExecutorService exec = Executors.newCachedThreadPool();

	public Restaurant() {
		exec.execute(chef);
		exec.execute(waiter);
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
}

class Meal{
	private final int orderNum;
	
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@Override
	public String toString() {
		return "meal #" + orderNum;
	}
}

class Chef implements Runnable{
	private Restaurant restaurant;
	private int count = 0;
	
	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@Override
	public void run() {
		try {
			while (! Thread.interrupted()){
				synchronized (this) {
					while (restaurant.meal != null){
						wait();
					}
				}
				
				if (++count > 10) {
					System.out.println("Out of food, closing!");
					restaurant.exec.shutdownNow();
				}
				
				System.out.print("Order up!   ");
				
				synchronized (restaurant.waiter) {
					restaurant.meal = new Meal(count);
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chef interrupted");
		}
	}
}

class Waiter implements Runnable{
	private Restaurant restaurant;
	
	public Waiter(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (! Thread.interrupted()){
				synchronized (this){
					while (restaurant.meal == null){
						wait();
					}
					
					System.out.println("Waiter got " + restaurant.meal);
					
					synchronized (restaurant.chef) {
						restaurant.meal = null;
						restaurant.chef.notifyAll();
					}
				}
			}
			
		} catch (InterruptedException e) {
			System.out.println("Waiter interrupted!");
		}
	}
}




