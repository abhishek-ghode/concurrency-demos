package com.seed.concurrent.executor;
//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : CalculateSumRunnble.java
* 
* @since   26th  September 2013 
* @author  Sucheta
* 
*  This class is a Runnable and  used by TestExecutor.java application.
*/

public class CalculateSumRunnable implements Runnable 
{
	private int val ;
	
	public CalculateSumRunnable(int val)
	{
		this.val = val ;
	}
	
	/** TODO:comment 
	 *  Following method calculates sum of numbers from 1 to val.
	 */
	
	public void run() 
	{
		int sum = 0 ;
		
		for (int i = 1 ; i <= val ; i++)
			sum += i ;
		
		System.out.println(Thread.currentThread().getName() + ":  Sum of first "
		+ val + " numbers = " + sum) ;
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
