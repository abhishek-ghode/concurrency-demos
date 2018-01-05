package com.seed.concurrent.executor;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : DemoCallableFuture.java 
* 
* @since  29th September 2013 
* @author Sucheta
* 
* This java application demonstrates use of Callable  and Future.
* Callable is submitted to Executor and returns the result which is retrieved
* from  Future.
* 
* We are using Executor framework to execute 100 tasks by  fixed number of threads 
* in parallel and use Future to get the result of the submitted tasks.
* 
* Uses  SquareCallable.java
*/
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


public class DemoCallableFuture 
{
	private static final int NTHREDS = 10;
	
	public static void main(String[] args) 
	{
		/** TODO:comment
		 * 
		 *  Create Executor Service with fixed thread pool having 10 Threads.
		 *  Create a list which will contain Future objects holding results 
		 *  returned by Callables.
		 */
		
		 ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		
		 List<Future<SquareBean>> list = new ArrayList<Future<SquareBean>>();
		    
		    for (int i = 1; i <= 100 ; i++) 
		    {
		      Callable<SquareBean> worker = new SquareCallable(i);
		      
		      Future<SquareBean>   submit = executor.submit(worker);
		      list.add(submit);
		      
		      /** TODO:activity1
		       * In order to show use of FutureTask class , put above two lines 
		       * in comments  and uncomment following 3 lines.
		       */
		     
		      
		     // FutureTask<SquareBean>  future =  new FutureTask<SquareBean>(worker) ;
		     // executor.execute(future);
		     // list.add(future);
		     
		      
		    }
		    		    
		    // Now retrieve the result
		    for (Future<SquareBean> future : list)
		    {
		      try {
		    	    SquareBean fb = future.get();
		            System.out.println( "Number : " + fb.getNumber()+ 
		            		            "  and  its Square is  = " + fb.getSquare());
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      } catch (ExecutionException e) {
		        e.printStackTrace();
		      }
		    }
		       
		    executor.shutdown();
	}

}
