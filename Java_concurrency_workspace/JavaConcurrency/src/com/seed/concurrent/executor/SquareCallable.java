package com.seed.concurrent.executor;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : SquareCallable.java
* 
* @since  29th  September 2013 
* @author Sucheta
* 
* This class demonstrates Use  of  Callable interface and for this uses
* SquareBean.java
* 
* Overridden Call() method returns the type which is specified in the 
* Generic declaration of the Callable<?> interface which is implemented 
* by  this class.
* Here  Call() method calculates Square of  the number passed  as the parameter
* while creating  object  of SquareCallable  class.  
*/

import java.util.concurrent.Callable;

public class SquareCallable  implements Callable<SquareBean> 
{
    private  SquareBean  fb ;   
	
	public  SquareCallable(int number)
	{
		fb = new SquareBean(number) ;
	}
	
    @Override
    public SquareBean  call() throws Exception 
    {
       int    value  = fb.getNumber();
       long   square = value * value ;
              
       fb.setSquare(square);
       
       return   fb;
    }  // end call.

}  // end of class FactorialCallable


