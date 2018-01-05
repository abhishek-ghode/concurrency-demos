package com.seed.concurrent.synchronizers;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : DemoCountDownLatch.java
* 
* @since  7th October 2013 
* @author Sucheta
* 
* This java application demonstrates use of the java.util.concurrent.CountDownLatch.
* 
* Uses class Speaker  which is of the type of Runnable
* 
* This application creates two threads using this runnable.
* Main thread ends only when the two child threads finish their work.
* 
*/

import java.util.concurrent.CountDownLatch;

public class DemoCountDownLatch 
{
	public static void main(String[] args)
	{
		// CountDownLatch is initialized with count 2 .
		
		CountDownLatch latch = new CountDownLatch(2);
	    Speaker  speaker = new Speaker(latch) ;
		
		Thread one = new  Thread(speaker , "Speaker ONE");
		Thread two = new  Thread(speaker , "Speaker TWO");
		
		one.start();
		two.start() ;
		
		// Main thread waits till CountDownLatch count reaches to zero 
		try {
			System.out.println("Now  " + latch.getCount() + "  speakers  will  address  you.");
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Main speaker concludes ! as number of remaining speakers is "
				+ latch.getCount());
		
	}

}

class Speaker  implements  Runnable
{
	CountDownLatch latch ;
	
	public Speaker(CountDownLatch latch)
	{
		this.latch = latch ;
	}
	public  void  run() 
	{
	  try 
	  {
		Thread.sleep(2000);
	  } catch (InterruptedException e)
	  {
	  e.printStackTrace();
	  }
	  System.out.println(Thread.currentThread().getName() + "  addressing...");
	  
	  this.latch.countDown();
	} // end run
} // end class speaker
