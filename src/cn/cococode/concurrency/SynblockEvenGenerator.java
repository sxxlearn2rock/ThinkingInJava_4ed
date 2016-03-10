package cn.cococode.concurrency;

public class SynblockEvenGenerator extends IntGenerator{
	private int mNum = 0;
	Object lockObj = new Object();
	@Override
	public int next() {
		synchronized(lockObj){
			++mNum;
			++mNum;
		}
		return mNum;
	}
	
}
