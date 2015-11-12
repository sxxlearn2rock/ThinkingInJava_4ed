package cn.sxx.thinkinginjava.chap15;

import java.util.LinkedList;
import java.util.Random;

public class RandomList<T> {
	private LinkedList<T> storage = new LinkedList<>();
	private Random rand = new Random(47);
	
	public void add(T e){
		storage.add(e);
	}
	
	public T select(){
		int index = rand.nextInt(storage.size());
		T res = storage.get(index);
		storage.remove(index);		
		return res;
	}
	
	public static void main(String[] args) {
		RandomList<Integer> rdi = new RandomList<Integer>();
		for (int i = 0; i <20; i++){
			rdi.add(i);
		}
		
		for (int i = 0; i <20; i++){
			System.out.print(rdi.select() + " ");
		}
	}

}
