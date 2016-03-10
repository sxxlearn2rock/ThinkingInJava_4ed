package cn.cococode.concurrency;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

class Toast{
	public enum Status{ DRY, BUTTERED, JAMMED}
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) {
		this.id = idn;
	}
	
	public void butter(){ status = Status.BUTTERED; }
	public void jam() { status = Status.JAMMED; }
	public Status getStatus(){ return status;}
	public int getId(){return id;}
	@Override
	public String toString() {
		return "Toast " + id + " " + status;
	}
	
}

class ToastQueue extends LinkedBlockingDeque<Toast>{}

class Toaster implements Runnable{
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue tq) {
		this.toastQueue = tq;
	}
	
	@Override
	public void run() {
		while (! Thread.interrupted()){
			try {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(++count);
				System.out.println(t);
				toastQueue.put(t);
			} catch (InterruptedException e) {
				System.out.println("Interupted! Toaster");
			}
			
		}
		System.out.println("Toast off");
	}
	
}

public class ToastOMatic {

}
