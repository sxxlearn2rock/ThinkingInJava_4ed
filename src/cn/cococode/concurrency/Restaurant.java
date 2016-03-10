package cn.cococode.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal{
	private final int orderNum;
	public Meal(int orderNum) { this.orderNum = orderNum;}
	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable{
	Restaurant restuarant;
	
	public WaitPerson(Restaurant r) {
		this.restuarant = r;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				synchronized (this) {
					while (restuarant.meal == null)
						wait();
				}
				System.out.println("waitperson got " + restuarant.meal);
				synchronized (restuarant.chef) {
					restuarant.meal = null;
					restuarant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Waitperson interrupted!");
		}
	}
	
}

class Chef implements Runnable{
	Restaurant restuarant;
	private int count = 0;
	
	public Chef(Restaurant r) {
		this.restuarant = r;
	}
	
	@Override
	public void run() {
		while (! Thread.interrupted()){
			try {
				synchronized (this) {
					while(restuarant.meal != null) 
						wait();
				}
				
				if (++count == 10) {
					System.out.println("Out of Food! Closing!");
					restuarant.exec.shutdownNow();
				}
				
				System.out.println("Order Up!");
				synchronized (restuarant.waitPerson) {
					restuarant.meal = new Meal(count);
					restuarant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("Chef interrupted!");
			}
		}
	}
	
}

public class Restaurant {
	Meal meal;
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	ExecutorService exec = Executors.newCachedThreadPool();
	public Restaurant() {
		exec.execute(waitPerson);
		exec.execute(chef);
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
	
}
