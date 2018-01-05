package com.seed.concurrent.synchronizers;
import java.util.concurrent.* ;

@SuppressWarnings (value="all")
public class MoneyExchanger implements Runnable
{
	
	 Exchanger exchanger = null;
	 Object    object    = null;

	    public MoneyExchanger(Exchanger exchanger, Object object) {
	        this.exchanger = exchanger;
	        this.object = object;
	    }

	    public void run() {
	        try {
	            Object previous = this.object;

	            this.object = this.exchanger.exchange(this.object);

	            System.out.println(
	                    Thread.currentThread().getName() +
	                    " exchanged " + previous + " for " + this.object
	            );
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
}

class Dollar
{
	int  dollars ;
	
	public  Dollar(int dollars)
	{
		this.dollars = dollars ;
	}
	
	public int  getDollars()
	{
		return  dollars ;
	}
	
	public  String toString()
	{
		return ("$" + dollars + " ");
	}
}  // end class  Dollar



class Rupee
{
    float  rupees ;
	
	public  Rupee(Dollar  dollars , float rate)
	{
		this.rupees = dollars.getDollars() * rate;
	}
	
	public  String toString()
	{
		return ("Rs." + rupees);
	}
}
