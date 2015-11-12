package cn.sxx.thinkinginjava.chap15;

import java.util.Iterator;

public class IterableFibonacci extends Fibonacci
	implements Iterable<Integer>{

	private int count;
	public IterableFibonacci(int count) { this.count = count;	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			@Override
			public boolean hasNext() {
				return count > 0;
			}

			@Override
			public Integer next() {
				count--;
				return IterableFibonacci.this.next();
			}
		};
	}
	
	public static void main(String[] args) {
		for (Integer i : new IterableFibonacci(20)) {
			System.out.print(i + " ");
		}
	}
	
}
