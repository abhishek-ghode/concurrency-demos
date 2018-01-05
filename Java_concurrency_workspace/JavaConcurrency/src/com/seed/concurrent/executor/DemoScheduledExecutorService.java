package com.seed.concurrent.executor;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : DemoScheduledExecutorService.java
* 
* @since  30th  September 2013 
* @author Sucheta
* 
* This java application demonstrates use of ScheduledExecutorService which is 
* An ExecutorService that can schedule commands to run after a given delay, 
* or to execute periodically. 
*   
*/

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DemoScheduledExecutorService
{

	public static void main(String[] args) 
	{
		ScheduledExecutorService scheduledExecutorService ;
		
		ScheduledFuture<String> scheduledFuture ;
			
		scheduledExecutorService = Executors.newScheduledThreadPool(5);
		
		/** TODO:comment
		 *        First a ScheduledExecutorService is created with 5 threads in.
		 *        Then an anonymous implementation of the Callable interface is created and 
		 *        assigned to callable  reference.
		 */
				
		Callable<String> callable = new  Callable<String>() {
	        public String call() throws Exception {
	            System.out.println("Executed!");
	            return ("Call from " + Thread.currentThread().getName());
	        } };
		
	   /**  TODO:comment
	    *   This callable  reference is  passed to the schedule() method. 
	    *   The two last parameters  of  schedule() method  specify that the Callable should be 
	    *   executed after 3 seconds  for  5  times.
	    */
	    
	    for (int i = 1 ; i <=5 ; i++)
	    {
	    	 scheduledFuture = scheduledExecutorService.schedule(callable , 3 , TimeUnit.SECONDS) ;
	    	       
		try {
			System.out.println("result = " + scheduledFuture.get());
		}
		catch (InterruptedException | ExecutionException e)
          {
			e.printStackTrace();
		  }
	    } // end for
	    
	    scheduledExecutorService.shutdown(); 
	    
	} // end main

} // end class
