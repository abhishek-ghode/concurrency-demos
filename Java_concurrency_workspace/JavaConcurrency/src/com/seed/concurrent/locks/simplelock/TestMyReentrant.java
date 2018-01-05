package com.seed.concurrent.locks.simplelock;
//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : TestMyReentrant.java
* 
* @since  24th September 2013 
* @author Sucheta
* 
* This application is the Test class of MyReentrant.java
* 
* Uses  MyReentrant.java
*/


import java.util.concurrent.locks.* ;

public class TestMyReentrant
{
   
    public static void main(String[] args)
	{
    	int initialValue = 100 ;
    	int increment = 15 ;
    	
		MyReentrant obj = new  MyReentrant(initialValue) ;
		
		obj.setValue(increment);
		
		obj.showCount();
	}
	
}



