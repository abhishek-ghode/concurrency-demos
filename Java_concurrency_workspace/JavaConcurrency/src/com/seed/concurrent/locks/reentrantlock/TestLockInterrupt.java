package com.seed.concurrent.locks.reentrantlock;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : TestLockInterrupt.java
* 
* @since  26th September 2013 
* @author Sucheta
* 
* This java application demonstrates use of lockInterruptibly() method from 
* ReentrantLock class. 
* Refer to description of method lockInterruptibly()  from Locks_Doc.txt
*/


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockInterrupt
{
	private  int  sharedVal = 0 ;
	private  ReentrantLock myLock ;
	
	public  TestLockInterrupt() 
	{
		sharedVal = 0 ;	
		myLock = new ReentrantLock() ;
		
	} //end of  constructor
	
	
	public static void main(String[] args) 
	{
		final TestLockInterrupt obj = new TestLockInterrupt() ;
		final Thread writer , reader ;
		
		/*
		 * TODO 1  :
		 * comment : Create reader thread which tries to acquire the lock with lockInterruptibly()
		 *           method , This method blocks till either lock is acquired or thread is 
		 *           interrupted while waiting for the lock. If the thread is interrupted then the 
		 *           thread can  not get the lock and it proceeds further.
		 */
		
		reader = new Thread(){
			
			public void run() {
				Thread.currentThread().setName("Reader") ;
				 boolean lockAcquired = false;
				 
				 try {
					    obj.myLock.lockInterruptibly();
					    
					    /*
					     * TODO 2   :
					     * Action   : Comment out above line. Remove comments of line specified below.
					     *            This will show difference in output when the thread uses lock()
					     *            instead of lockInterruptibly() method.
					     *            You will find that when lock() is used it can not be interrupted
					     *            while waiting for the lock. But it will be interrupted during 
					     *            its sleep.
					     */
					    
					    // Remove comments of following line for ToDo 2 : Action
					    // obj.myLock.lock() ;
					    					    
					    lockAcquired = true;
					    
					    System.out.println("Lock acquired ,  Reader is sleeping now .....");
					    
					    Thread.sleep(3000) ;
					    
				      } // end try 
			     catch (InterruptedException e1)
				  { 	
			    	 if (lockAcquired)
			    		 System.out.println("Reader acqired lock but Reader's sleep  interrupted.")  ; 
			    	 else
			    	    System.out.println("Reader interrupted before getting the lock ...so could not Sleep."); 
				       //  e1.printStackTrace();
				  }
					 finally
					  {
						/*
						 * TODO   3:
						 * comment : If lock is acquired release it and print secret number and 
						 *           and reset the lockAcquired flag.
						 */
					    if(lockAcquired)
					    {	obj.myLock.unlock();
					        System.out.println("Reader has read  secret number :  " + obj.sharedVal);
					        lockAcquired = false ;
					    }
					    else
					    	System.out.println("Reader did not read the secret number");
					  }
					
				
			} // end run
		};
		
		
		/*
		 * TODO   4:
		 * comment : Create a writer thread which tries to acquire the lock with lock() method.
		 */
		
         writer = new Thread(){
			
			public void run() {
				
			  boolean lockAcquired = false;
			  Thread.currentThread().setName("Writer") ;
				
			  try {
					    obj.myLock.lock();  
					    lockAcquired = true;
					    obj.sharedVal++ ;
					    System.out.println("Writer has  written: " + obj.sharedVal);
					    
			            /*
			             * TODO   5:
			             * comment : Execute first as it is with given sleep time so that you can see 
			             *           that Reader thread  is interrupted while waiting for the lock.	    
			             */
					    
			            Thread.sleep(1500) ;
			            
			           /*
			            * TODO   6:
			            * comment : Execute this program by reducing sleep time to 500.
			            *           So remove Thread.sleep(1500) and write Thread.sleep(500)
			            *           so that you can see that now Reader thread is not interrupted
			            *           and it gets the lock before interruption.
			            *         
			            *           Writer thread now interrupts the Reader thread.
			            */
			            
			            reader.interrupt() ;
					   } // end try 
				  catch (InterruptedException e1)
				  { 
					  System.out.println("Writer's  sleep  interrupted.")  ;
					  e1.printStackTrace();    }
				  finally
				  {
				    if(lockAcquired)
				    {
				    	obj.myLock.unlock();  // release the lock.
				    	lockAcquired = false ;
				    }
				    
				  }
				  
			 } // end run
		};
		
		
		writer.start();
		
		/*
		 * TODO comment : Main Thread sleeps after starting writer thread, so that Reader thread 
		 *                is started after some delay.
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reader.start() ;
					
	}   // main ends

}  // class TestLockInterrupt ends.
