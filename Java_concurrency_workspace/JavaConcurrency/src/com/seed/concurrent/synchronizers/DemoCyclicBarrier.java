package com.seed.concurrent.synchronizers;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : DemoCyclicBarrier.java
* 
* @since  9th October 2013 
* @author Sucheta
* 
* This java application demonstrates use of CyclicBarrier which lets a group of threads wait 
* on a barrier and then proceed after the last thread arrives. 
*  
* Uses TrekkerRunnable  class which is the Runnable.
*  
*/

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DemoCyclicBarrier 
{
    /* Define Runnable which will be used as 1st barrier point by the CyclicBarrier. */
	static Runnable barrier1Stop = new Runnable() {
	    public void run() {
	        System.out.println("Both  Trekkers reached  Barrier Stop 1. \n");
	    }
	};
	
	/* Define Runnable which will be used as 2nd barrier point by the CyclicBarrier. */
	static Runnable barrier2Stop = new Runnable() {
	    public void run() {
	        System.out.println("Both  Trekkers reached Barrier Stop 2. \n");
	    }
	};
	
	public static void main(String[] args) 
	{
		CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Stop);
		CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Stop);

		TrekkerRunnable  trekker1 =
		        new TrekkerRunnable(barrier1, barrier2); // First thread 

		TrekkerRunnable  trekker2 =
		        new TrekkerRunnable(barrier1, barrier2); // Second thread 
		
		new Thread(trekker1 , "Trekker_1").start();
		new Thread(trekker2 , "Trekker_2").start();
	}

}

class TrekkerRunnable  implements  Runnable
{
	CyclicBarrier barrier1 = null;
    CyclicBarrier barrier2 = null;

    public TrekkerRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) 
    {
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() +
                                " waiting at Barrier Stop 1");
            this.barrier1.await();

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +
                                " waiting at Barrier Stop 2");
            this.barrier2.await();

            System.out.println(Thread.currentThread().getName() +
                                " has  crossed both barriers and completed the Trek.!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}