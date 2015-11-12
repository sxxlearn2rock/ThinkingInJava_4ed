package cn.sxx.thinkinginjava.chap21;


public class LiftOff implements Runnable
{
	protected int countDown = 5;
	private static int taskDown = 0;
	private final int id = taskDown++;
	
	public LiftOff()
	{
	}
	
	public LiftOff(int countDown)
	{
		this.countDown = countDown;
	}
	
	public String status()
	{
		return "#" + id + "-->" +(countDown > 0 ? countDown : "LiftOff!")+",";
	}
	
	@Override
	public void run()
	{
		while (countDown-- > 0)
		{
			System.out.println(status());
			Thread.yield();
		}
		
	}
}
