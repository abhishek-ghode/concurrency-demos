package com.seed.concurrent.synchronizers;

import java.util.concurrent.*;

public class DemoSemaphore 
{
	public static void main(String[] args) 
	{
	  
		ATMrunnable  runnable =  new  ATMrunnable() ;
		
		Thread Person1 = new Thread(runnable , "Tushar");
		Thread Person2 = new Thread(runnable , "Anita");
		Thread Person3 = new Thread(runnable , "Madhuri");
		Thread Person4 = new Thread(runnable , "Vinay");
		Thread Person5 = new Thread(runnable , "Neeta");
		
		Person1.start();
		Person2.start();
		Person3.start();
		Person4.start();
		Person5.start();
		
	} // end main

}  // End  class DemoSemaphore



class   ATMrunnable  implements  Runnable
{
	private  Semaphore  semaphore ;
	private  final  int totalATMs  = 2 ;
	private  final  int maxPersonsAllowed = totalATMs ;
       
	public ATMrunnable()
	{
		semaphore = new Semaphore(maxPersonsAllowed , true);
	}
	
	
	public  void  run()
	{
	   this.acquireATM() ;
	   
	  
	}
	
	 private void acquireATM()
	 {
		    try 
		    {
	        	   System.out.println(Thread.currentThread().getName() + "  waiting  for  ATM.");
	        	   
	               semaphore.acquire();
	               
	               System.out.println(semaphore.getQueueLength() + " Persons waiting for ATM now ********");
                   
	               //Person  gets ATM
	               System.out.println(Thread.currentThread().getName() + " Withdrawing money " + 
	                                  " from ATM machine");
	            
	               Thread.sleep(1000);
	              

	        } catch (InterruptedException ie) { ie.printStackTrace();
	        } finally 
	        {
	            semaphore.release();
	            System.out.println(Thread.currentThread().getName() + " finished  with ATM machine.");
	        }
	    }

	

}
