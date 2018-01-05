package com.seed.concurrent.locks.simplelock;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : MyReentrant.java
* 
* @since  24th September 2013 
* @author Sucheta
* 
* This class demonstrates meaning of a ReentrantLock. This is demonstrated by the use of hold value
* of a thread that holds any ReentrantLock.
* When we use Syncronized keyword , implicit locks are used internally but they are not Reentrant.
* For Meaning of Reentrancy refer to Section of ReentrantLock from Locks_Doc.txt
*
**/

import java.util.concurrent.locks.ReentrantLock;

public class MyReentrant
{
	private ReentrantLock reentrant ; 
	private int value ;
	
	public MyReentrant(int value)
	{
		reentrant = new ReentrantLock() ;
		this.value = value ;
	}
	
	public void  setValue(int increment)
	{
		/*
		 * TODO 1:
		 * comment : Thread wants to enter critical section , so it should acquire the lock.
		 *           Then in the showCount method, another critical section , so it will again
		 *           acquire the lock. This means that  thread holds the lock twice. 
		 *           Hence it is release twice. 
		 */
		reentrant.lock();  
		value = value + increment ; 
		
		System.out.println(Thread.currentThread().getName() + "  holds " + 
                reentrant.getHoldCount() + " locks in setValue().");
		
		showCount() ;
		
		/*
		 * TODO 2: 
		 * comment : Release the lock while leaving critical section of this method and check for 
		 * its hold value value.
		 */
		
		reentrant.unlock();  
		
		System.out.println(Thread.currentThread().getName() + "  holds " + 
                reentrant.getHoldCount() + " locks ending setValue().");
	}
	
	public void showCount()
	{
		/*
		 * TODO 3:
		 * comment : Get the lock to enter critical section of this method.
		 */
		
		reentrant.lock();
		
		System.out.println("value is = "  + value);
		
		System.out.println(Thread.currentThread().getName() + "  holds " + 
                reentrant.getHoldCount() + " locks in showCount");
		
		/*
		 * TODO 4:
		 * comment : Release the lock while leaving critical section of this method
		 *           and check for its hold value value.
		 */
		reentrant.unlock();
		
		System.out.println(Thread.currentThread().getName() + "  holds " + 
                reentrant.getHoldCount() + " locks while ending showCount");
	}
}
