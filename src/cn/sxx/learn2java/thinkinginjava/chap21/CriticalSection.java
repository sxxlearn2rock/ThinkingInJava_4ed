package cn.sxx.learn2java.thinkinginjava.chap21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CriticalSection {
	//Test the two different approaches
	static void testApproaches(PairMannager pman1, PairMannager pman2){
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator 
		pm1 = new PairManipulator(pman1),
		pm2 = new PairManipulator(pman2);
		PairChecker
		pChecker1 = new PairChecker(pman1),
		pChecker2 = new PairChecker(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pChecker1);
		exec.execute(pChecker2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted!");
		}
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		PairMannager
			pman1 = new PairManager1(),
			pman2 = new PairManager2();
		testApproaches(pman1, pman2);
	}
}

class Pair{		//not thread-safe
	private int x,y;
	public Pair(int x, int y) {
	this.x = x;
	this.y = y;
	}
	
	public Pair() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void incrementX(){
		x++;
	}
	
	public void incrementY(){
		y++;
	}

	public String toString(){
		return "x: " + x + " y: " + y;
	}
	
	public void checkState(){
		if (x != y) {
			throw new PairValueNotEqualException();
		}
	}
	
	public class PairValueNotEqualException extends RuntimeException{
		public PairValueNotEqualException() {
			super("Pair value not equal: " + Pair.this);
		}
	}
}

abstract class PairMannager{
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> pairStorage = 
			Collections.synchronizedList(new ArrayList<Pair>());
	
	public synchronized Pair getPair(){
		return new Pair(p.getX(), p.getY());
	}
	
	protected void store(Pair p){
		pairStorage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException ignore) {
		}
	}
	
	public abstract void increment();
}


class PairManager1 extends PairMannager{

	@Override
	public synchronized void increment() {		
		p.incrementX();
		p.incrementY();
		store(getPair());
	}	
}

class PairManager2 extends PairMannager{
	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

class PairManipulator implements Runnable{
	private PairMannager pm;
	
	public PairManipulator(PairMannager pm) {
		this.pm = pm;
	}
	
	@Override
	public void run() {
		while (true){
			pm.increment();
		}
	}
	
	@Override
	public String toString() {
		return "Pair: " + pm.getPair() + " CheckCounter = " + pm.checkCounter.get();
	}
}

class PairChecker implements Runnable{
	private PairMannager pm;
	
	public PairChecker(PairMannager pm) {
		this.pm = pm;
	}
	
	@Override
	public void run() {
		while (true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();;
		}
	}	
}




