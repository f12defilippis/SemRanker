package com.flol.semrankercommon.util;

public class SemRankerUtil {

	private static Integer MIN_THREAD_WAIT = 4000;
	private static Integer MAX_THREAD_WAIT = 8000;
	
	
	public static void wait(Integer sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	public static void waitForFreeThreads()
	{
		try {
			Thread.sleep(NumberUtil.getRandomInteger(MIN_THREAD_WAIT, MAX_THREAD_WAIT));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
