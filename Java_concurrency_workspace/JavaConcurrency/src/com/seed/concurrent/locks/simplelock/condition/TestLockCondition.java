package com.seed.concurrent.locks.simplelock.condition;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : Test_ConditionBuffer.java
* 
* @since  26th September 2013 
* @author Sucheta
* 
* This java application creates 2 reader threads and one writer thread
* which access a buffer based on conditions.
* 
* This application uses  ConditionBuffer class. 
*   
*/

public class TestLockCondition
{

	public static void main(String[] args) 
	{
		final LockCondition buf = new LockCondition(10);
		
		Thread reader1 = new Thread("Reader_1")
		{
			public void run() 
			{
			 for (int i = 1 ; i <= 10 ; i++)
			 {
			   try
			   {
				 String str = buf.fetch();
				 System.out.println(Thread.currentThread().getName() + " Got data as = "
						 + str); 
				 Thread.sleep(500) ;
				} catch (InterruptedException e) 
				{
					System.out.println(Thread.currentThread().getName() + " has problem in reding from buffer.");
					e.printStackTrace(); }
			 } // end for
			} // end run
		};
		
		Thread reader2 = new Thread("Reader_2")
		{
			public void run() 
			{
				 for (int i = 1 ; i <= 10 ; i++)
				 {
				   try
				   {
					 String str = buf.fetch();
					 System.out.println(Thread.currentThread().getName() + " Got data as = "
							 + str); 
					 Thread.sleep(500) ;
					} catch (InterruptedException e) 
					{
						System.out.println(Thread.currentThread().getName() + " has problem in reading from buffer.");
						e.printStackTrace(); }
				 } // end for
			}// end run
			
		};
		
		Thread writer = new Thread("Writer")
		{
			public void run() 
			{
				for (int i = 1 ; i <= 10 ; i++)
				 {
				   try
				   {
					 String str = "Data:" + i + " " ;  
					 buf.deposit(str);
					 System.out.println(Thread.currentThread().getName() + " has written " + str);
					 Thread.sleep(500) ;
					} catch (InterruptedException e) 
					{
						System.out.println(Thread.currentThread().getName() + " has problem in writing from buffer.");
						e.printStackTrace(); }
				 } // end for
			} // end run
		};
		writer.start() ;
		reader1.start() ;
		reader2.start();
		
	}

}
