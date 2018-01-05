package com.seed.concurrent.synchronizers;

/**
 * Phaser: A reusable synchronization barrier, similar in functionality to CyclicBarrier 
 * and CountDownLatch but supporting more flexible usage. 
 */

import java.util.concurrent.*;
		 
public class DemoPhaser 
{  
		 
		   public static void main(String[] args) {
		 
		      Phaser phaser = new Phaser();
		 
		      Thread t1 = new MyThread(phaser,1000);
		      Thread t2 = new MyThread(phaser,3000);
		      Thread t3 = new MyThread(phaser,5000);
		      t1.start();
		      t2.start();
		      t3.start();
		 
		   }
}

class MyThread extends Thread {
	 
	   Phaser phaser;
	   int sleep;
	 
	   MyThread(Phaser phaser, int sleep){
	      this.phaser=phaser;
	      this.sleep=sleep;
	   }
	 
	   public void run() {
	      phaser.register();
	      System.out.println(this.getName() + " begin");
	      try {
	         Thread.sleep(sleep);
	      } catch (Exception e) { 
	      }
	      phaser.arriveAndAwaitAdvance();
	      System.out.println(this.getName() + " middle");
	      try {
	         Thread.sleep(sleep);
	      } catch (Exception e) { 
	      }
	      System.out.println(this.getName() + " end");
	   }
	}
