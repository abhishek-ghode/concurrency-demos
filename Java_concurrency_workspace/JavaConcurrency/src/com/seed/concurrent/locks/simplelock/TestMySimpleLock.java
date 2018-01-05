package com.seed.concurrent.locks.simplelock;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : TestMySimpleLock.java
* 
* @since   September 2013 
* @author  Sucheta
* 
*  This file is  the Testing application which demonstrates use of 
*  ReentrantLock  and use of simple locks by using the methods , 
*  lock() and unlock().
*  
*  Uses MySimpleLock.java
*/

public class TestMySimpleLock {

	public static void main(String[] args) 
	{
		MySimpleLock runnable = new MySimpleLock();
		
		Thread user1 = new Thread(runnable , "User1");
		Thread user2 = new Thread(runnable , "User2");
		Thread user3 = new Thread(runnable , "User3");
		
		user1.start() ;
		user2.start() ;
		user3.start() ;

	}

}

