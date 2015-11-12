package cn.sxx.thinkinginjava.chap14;

import java.util.ArrayList;
import java.util.List;

class CountedInteger{
	private static long counter;
	private final long id = counter++;
	@Override
	public String toString() {
		return Long.toString(id);
	}
	
}

public class FilledList<T> {
	private Class<T> type;
	
	public FilledList(Class<T> type) {
		this.type = type;
	}
	
	public List<T> create(int nElements){
		List<T> result = new ArrayList<T>();
		
		for (int i = 0; i < nElements; ++i){
			try {
				result.add(type.newInstance());
			} catch (InstantiationException e) {
				System.out.println("1");
			} catch (IllegalAccessException e) {
				System.out.println("2");
			}
			
		}
		return result;
	}

	public static void main(String[] args) {
		FilledList<CountedInteger> fl = 
				new FilledList<CountedInteger>(CountedInteger.class);
		System.out.println(fl.create(15));
	}
}
