package cn.sxx.thinkinginjava.chap21;

public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	public synchronized static int nextSerialNumber(){
		return serialNumber++;
	}
}
