package com.seed.concurrent.executor;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : SquareBean.java
* 
* @since  29th September 2013 
* @author Sucheta
* 
* This is a bean class having 2 properties , number  and  its  square and 
* their Getter and Setter methods.
* 
*/

public class SquareBean 
{
	   private  int    number ;
	   private  long   square ;
	   
	   public SquareBean(int number)
	   {
		   this.number = number ;
	   }
	   	   
	   public int getNumber()
	   {
		return number;
	   }

	   public long getSquare() 
	   {
		  return square;
	   }

	   public void setSquare(long  square) 
	   {
		this.square = square;
	   }
}
