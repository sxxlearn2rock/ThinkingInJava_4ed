package cn.sxx.learn2java.thinkinginjava.chap21;

public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public abstract int next();
	public void cancel() { canceled = true; }
	public boolean isCanceled() { return canceled; }
}
