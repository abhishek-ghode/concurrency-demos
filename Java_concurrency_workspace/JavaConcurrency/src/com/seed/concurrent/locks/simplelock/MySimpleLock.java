package com.seed.concurrent.locks.simplelock;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : MySimpleLock.java
* 
* @since  23rd September 2013 
* @author Sucheta
* 
* This is the Runnable class which is the demo of ReentrantLock.
* This is similar to use of  Synchronized  keyword. Used by Test_MySimpleLock.java
*  
* Uses  MyAccount.java 
*/

import com.seed.concurrent.locks.MyAccount;

import java.util.concurrent.locks.*;

public class MySimpleLock implements Runnable
{
	private  MyAccount  account;
	private  Lock       accountLock  ;
	final    private  int initialBalance = 100 ;
	boolean  fairness ;
	
	
	public  MySimpleLock() 
	{
		account = new MyAccount(initialBalance);
		
		/* TODO:1 
		 * Action : Here a fair ReentrantLock is created , as parameter of its constructor is true.
		 *          Because of fair ReentrantLock , each thread gets fair chance of execution.
		 *          Execute this as it is. 
		 */
		
		fairness = true ;
		accountLock = new ReentrantLock(fairness) ;  // Explicit lock object is created.
		
		/*
		 * TODO:2 
		 * Action : Remove fairness parameter from the constructor , for that remove following comments.
		 *          This means now reentrant Lock is not fair and then again execute Test_SimpleLock
		 *          application and show the difference in the output. 
		 *          Now some threads will get repeated chance of execution and some threads will not get 
		 *          any chance of execution. 
		 */
		
         /* Following comments  to be removed for above ToDo2 Action */   
		//		  accountLock = new ReentrantLock() ;
		
	}     // end constructor.
	
	   
    public void run()
     {
	   for (int i = 1 ; i <=5 ; i++)
	      makeWithdraw() ;
	   
	 }    // end run
    
    public  void  makeWithdraw()
    {
    	/*
    	 * TODO:3
    	 * comment : Here we have not declared the method as synchronized or no synchronized block 
    	 *           is used. Synchronized keyword is associated with implicit lock. 
    	 *           But here when any thread enters critical section of this method it tries to get
    	 *           the lock. If lock is available then thread gets the same. Otherwise it gets 
    	 *           blocked here until it gets the lock. Once the lock is available, thread proceeds. 
    	 */
    	
    	accountLock.lock() ; 
    	
     	Thread t = Thread.currentThread();
     	String name = t.getName() ;
     	
    	if  (account.getBalance() < 0)
    	    System.out.println(t.getName() + " : Account is overdrawn. ********  ") ;
    	else if (account.getBalance() < 10)
	        System.out.println(t.getName() + " : Insufficient Balance  ......") ;
	    else
	    {	
	    	System.out.println(name + " finds previous balance = " + account.getBalance()); 
	    	
	    	account.withDraw(10);
	    	
    	  	System.out.println(Thread.currentThread().getName() + "  has withdrawn Rs. 10 ") ;
    	  	System.out.println(name + " says new balance is : " + account.getBalance()+ "\n");
    	  	
    	  	 try { Thread.sleep(500) ;  } 
    	  	 catch (InterruptedException e) 
    	 	       {  e.printStackTrace(); }
    		  	
	    }
    	
    	/*
    	 * TODO:4
    	 * comment : Now Thread has finished critical section of the method , hence it has to 
    	 *           release the lock.
    	 */
    	
    	accountLock.unlock();
    	
    }  // end method makeWithdraw()
    
}    // end class SimpleLock
    
