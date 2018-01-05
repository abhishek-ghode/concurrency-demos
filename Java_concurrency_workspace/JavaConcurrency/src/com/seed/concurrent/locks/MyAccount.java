package com.seed.concurrent.locks;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : MyAccount.java
* 
* @since  23rd  September 2013 
* @author Sucheta
* 
* This is a bean class used by MySimpleLock class from same package. 
* 
*/


public class MyAccount 
{
	 private int balance ;
		
		public  MyAccount(int balance)
		{
			this.balance = balance ;
		}
		
		public void withDraw(int amount)
		{
			if (balance < amount)
				System.out.println("Insufficient Balance Problem ...");
			else 
			    balance = balance - amount ;
		}
		
		public int getBalance()
		{
			return balance ;
		}
}
