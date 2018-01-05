package com.seed.concurrent.locks.readwritelock;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : TestReadWriteLocks.java
* 
* @since  24th September 2013 
* @author Sucheta
* 
* This is test application of MyBean class which uses ReentrantReadWriteLock.
* This  application creates 2 reader Threads which read the data of MyBean and one writer thread 
* which writes or sets data of MyBean.
* 
* For more information on ReentrantReadWriteLock refer to Locks_doc.txt
*/


public class TestReadWriteLocks 
{
    final MyBean bean = new MyBean() ;
        
	
	public static void main(String[] args)
	{
		final MyBean bean = new MyBean() ;
        
		Writer  writerRunnable = new Writer(bean);
		
		Thread writer = new Thread(writerRunnable, "WriterThread1");
		
		Thread reader1 = new Thread(new  Reader(bean) , "Reader_1");
				
		Thread reader2 = new Thread(new Reader(bean), "Reader_2");
		
		writer.start() ;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reader1.start() ;
		reader2.start();
		System.out.println(Thread.currentThread().getName() + "  ends. ") ;
	} // end main
	
} // end class TestReadWriteLocks
