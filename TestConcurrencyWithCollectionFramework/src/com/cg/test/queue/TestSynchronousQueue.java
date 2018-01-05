package com.cg.test.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.cg.util.Consumer;
import com.cg.util.Producer;

public class TestSynchronousQueue {
	public static void main(String[] args) {
		BlockingQueue<String> drop = new SynchronousQueue<String>();
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
		
		
	}
}
