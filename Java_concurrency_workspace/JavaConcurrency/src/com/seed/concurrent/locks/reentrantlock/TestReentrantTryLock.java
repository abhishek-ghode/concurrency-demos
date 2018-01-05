package com.seed.concurrent.locks.reentrantlock;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : TestReentrantTryLock.java
* 
* @since  26th September 2013 
* @author Sucheta
* 
* This java application demonstrates working of tryLock() method from ReentrantLock class.
* We can specify waiting time for a lock using one version of this method.
* 
* For more information  about this method refer to Locks_doc.txt
* 
*/

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class TestReentrantTryLock 
{
	private   String  sharedMsg ;
	private   ReentrantLock myLock ;
	
	public  TestReentrantTryLock()
	{
		sharedMsg = null ;	
		myLock = new ReentrantLock() ;
	}
	
	public static void main(String[] args) 
	{
		final TestReentrantTryLock  lock = new TestReentrantTryLock() ;
		 
		/*
		 * TODO 1  :   
		 * comment :  Create  writer thread which first gets the lock and then writes the message.
		 *            For getting the lock it uses  lock() method, so it gets blocked till it 
		 *            acquires the lock.
		 *            After writing the message, Writer thread sleeps for 4 seconds , so during this 
		 *            period it is holding the lock. Now in this period Reader thread tries for the lock
		 *            but it will not get it. Reader will get the lock when Writer's sleep is over.
		 */
		
		Thread writer = new Thread("Writer"){
			
			public void run() {
			  
			  String myName = Thread.currentThread().getName() ;
				
			  lock.myLock.lock();
			  lock.sharedMsg = " Welcome to Seed!  \n" ;
			  
			  try { 
				     System.out.println(myName + " has  written: " + lock.sharedMsg);
				     Thread.sleep(4000) ; 
			       } // end try
			  catch (InterruptedException e){ e.printStackTrace(); }
			  finally { 
				        /*
				         * TODO 2  : 
				         * comment : In any case lock must be unlocked , so we put it in finally block.
				         */
				  
				   	    lock.myLock.unlock();
				   	  }
			  
			 
			} // end run
		};
		
		
		   /*
		    * TODO 3  : 
		    * comment : Create the Reader thread which tries to get the lock using 
		    *           trylock(waittime,timeunit) method repeatedly. If the lock is held by the 
		    *           Writer thread , Reader will wait in trylock() method for waiting time but 
		    *           will not get the lock. 
		    *           When the lock is released by the writer thread , it is acquired by the 
		    *           Reader thread.
		    */
		
		   Thread reader = new Thread("Reader"){
			
			public void run() {
				String    myName = Thread.currentThread().getName() ;
				boolean   acquiredLock  = false ;
				
				while(true)
				{try {
					   acquiredLock = lock.myLock.tryLock(1000, TimeUnit.MILLISECONDS);
			    	    	
				       if (acquiredLock)
				          { System.out.println("\n" + myName + " has acquired lock and read  message as : " + 
				                               lock.sharedMsg);
				            break ;
				          }
				       else
				       {  System.out.println(myName + " was waiting for the lock but could not get it. " +
				       		"I will try for the lock after some time."); }
			              Thread.sleep(1000) ;
				       }// end else
				catch (InterruptedException e)
				       { e.printStackTrace(); }
				}  // end while     
			      
			   if (acquiredLock)
				   lock.myLock.unlock();
			} // end run
		};
		
		writer.start();
		
		/*
		 * TODO 4  :
		 * comment : Main thread sleeps here so as to produce delay in starting Reader thread. 
		 */
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reader.start() ;
		
		
		
	} // end main

}// end TestReentrantLock 
