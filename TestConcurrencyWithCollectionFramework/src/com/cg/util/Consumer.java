package com.cg.util;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<String> drop;

	public Consumer(BlockingQueue<String> d) {
		this.drop = d;
	}

	public void run() {
		try {

			String msg = null;
			
			while (true){
				
				System.out.println("Peek operation on Queue: "+drop.peek());
				
				if(((msg = drop.take()).equals("DONE")))
					break;
				
				
				System.out.println(msg);
			}

			
/*			while (!((msg = drop.take()).equals("DONE"))){
				System.out.println(msg);
			}
*/		} catch (InterruptedException intEx) {
			System.out.println("Interrupted! "
					+ "Last one out, turn out the lights!");
		}
	}
}