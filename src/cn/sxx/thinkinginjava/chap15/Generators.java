package cn.sxx.thinkinginjava.chap15;

import java.util.ArrayList;
import java.util.Collection;

import cn.sxx.thinkinginjava.chap15.coffee.Coffee;
import cn.sxx.thinkinginjava.chap15.coffee.CoffeeGenerator;
import cn.sxx.thinkinginjava.util.Generator;

public class Generators {
	public static <T> Collection<T> 
		fill(Collection<T> coll, Generator<T> gen, int n){
		for (int i = 0; i < n; ++i){
			coll.add(gen.next());
		}
		return coll;
	}
	
	public static void main(String[] args) {
		Collection<Coffee> coffees = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 5);
		for (Coffee coffee : coffees) {
			System.out.println(coffee);
		}
		
		System.out.println();
		
		Collection<Integer> fib = fill(new ArrayList<Integer>(), new Fibonacci(), 12);
		for (Integer integer : fib) {
			System.out.print(integer + " ");
		}
	}
}
