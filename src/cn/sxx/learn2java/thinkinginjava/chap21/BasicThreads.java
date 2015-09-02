package cn.sxx.learn2java.thinkinginjava.chap21;

public class BasicThreads
{

	public static void main(String[] args)
	{
		for (int i=0; i < 5; i++)
		{
			new Thread(new LiftOff()).start();
		}
		System.out.println("Waiting for launch!");
	}

}
