package com.seed.concurrent.executor;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : TestExecutor.java
* 
* @since  26th September 2013 
* @author Sucheta
* 
* This java application demonstrates basic use of Executor.
* It uses ExecutorService as it is sub interface of Executor and gives more methods 
* as compared to Executor which declares only one method i.e. execute().  
* 
* Uses  CalculateSumRunnable class as the Runnable.
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestExecutor {

	private static final int NTHREDS = 5;
	
	public static void main(String[] args)
	{
		  /** TODO:comment 
		   *  Create Thread Pool of  5  threads
		   *  
		   *  Discuss : This Creates a thread pool that reuses a fixed number of threads operating off a 
		   *  shared unbounded queue. At any point, at most nThreads threads will be active 
		   *  processing tasks. If additional tasks are submitted when all threads are active, 
		   *  they will wait in the queue until a thread is available. 
		   *  If any thread terminates due to a failure during execution prior to shutdown, 
		   *  a new one will take its place if needed to execute subsequent tasks. 
		   *  The threads in the pool will exist until it is explicitly shutdown. 
		   */
		
		
		  ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		  
		  /** TODO:activity1
		   *  1. Put above line in comments  and
		   *  2. Uncomment following line. 
		   *    
		   *  So now  when you run this application discuss how executer is created with single thread
		   *  and multiple tasks are managed by this single thread sequentially.
		   */
		   
		  
	  //  ExecutorService executor = Executors.newSingleThreadExecutor();
		 
		  /** TODO:activity2
		   *  1. Put above 2 lines in comments  and
		   *  2. Uncomment following line. 
		   *  
		   *  So now  when you run this application discuss how executer is created with
		   *  
		   */
		  
	  
	  // ExecutorService executor = Executors.newCachedThreadPool();         
		  
		  /**  TODO:comment
		   *   Discuss 
		   *   Above statement when Creates a thread pool that creates new threads as needed, 
		   *   but will reuse previously constructed threads when they are available. 
		   *   These pools will typically improve the performance of programs that execute 
		   *   many short-lived asynchronous tasks. 
		   *   Calls to execute will reuse previously constructed threads if available. 
		   *   If no existing thread is available, a new thread will be created and added to the pool. 
		   *   Threads that have not been used for sixty seconds are terminated and removed from 
		   *   the cache. Thus, a pool that remains idle for long enough will not consume any resources.
		   */
		  
		  
		/**   TODO:comment
		 *    In the following  for loop we are creating 50 tasks i.e. Runnables
		 *    and they are executed by the specific executor we have created.
		 */
		  
		  
	    for (int i = 1 ; i <= 50; i++) 
	    {
	      Runnable worker = new CalculateSumRunnable(i);
	      
	      executor.execute(worker);
	    }
	    
	    /** TODO:comment
	     *  
	     *  This will make the executor accept no new threads and finish all existing threads 
	     *  in the queue
	     * 
	     */
	    
	    executor.shutdown();
	    
	    /** TODO:comment
	     *  Wait until all threads are finish
	     * 
	     */
	    
	    try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
			
		} catch (InterruptedException e) 
		{
				e.printStackTrace();
		}
	    System.out.println("Finished all threads");

	}

}
