package cn.sxx.thinkinginjava.test;

import java.util.Date;

public class House implements Cloneable{
	private int id;
	private double area;
	private Date when;
	
	public House(int id, double area, Date when) {
		this.id = id;
		this.area = area;
		this.when = when;
	}
	
	public int getId() {
		return id;
	}

	public double getArea() {
		return area;
	}

	public Date getWhen() {
		return when;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
