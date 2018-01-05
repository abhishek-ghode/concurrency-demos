package com.seed.concurrent.locks.simplelock.condition;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : ConditionBuffer.java
* 
* @since  25th September 2013 
* @author Sucheta
* 
* This class demonstrates use of Condition objects while working with locks
* and methods of Condition objects.
* 
* This class is used by Test_ConditionBuffer.java
*/

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition
{
	  private final String[] buffer;
	  private final int capacity;
			 
	  private int front;
	  private int rear;
	  private int count;
			 
	  private final Lock lock = new ReentrantLock();
		 
	  private final Condition bufNotFull = lock.newCondition();
	  private final Condition bufDataReady = lock.newCondition();
			 
	  public LockCondition(int capacity) 
	  {
		this.capacity = capacity;
		buffer = new String[capacity];
		front = rear = count = 0 ;
	  }
			 
      public void deposit(String data) throws InterruptedException 
      {
		lock.lock();
			 
		 try {
		        while (count == capacity)   /* buffer is full */
		        {     
		    	    bufNotFull.await()   ;  /* wait till signalled by other thread as 
		    	                               buffer is not full. */
		        }
		      
		        /* Read data into buffer Q as buffer is not full */
			    buffer[rear] = data;
			    rear = (rear + 1) % capacity;
			    count++;
			 
			    bufDataReady.signalAll();   /* Signal to all threads waiting for new data 
			                                   ready in the buffer. */
		     } // end try
		  finally { lock.unlock(); }
		
	   } // end method deposit.
		
      
	   public String fetch() throws InterruptedException
	   {
		 lock.lock();
			 
		 try {
		       while (count == 0)        /* buffer empty  */
		       {
			      bufDataReady.await();  /* Wait till the condition data ready in the 
			                                buffer, is true. */
			   }
			 
			   String result = buffer[front];
			   front = (front + 1) % capacity;
			   count--;
			 
			   bufDataReady.signal();
			 
			   return result;
			   
			 } //end try
		 finally {   lock.unlock();  }
			    
	   }  //end method  fetch.

}  // end class ConditionBuffer
