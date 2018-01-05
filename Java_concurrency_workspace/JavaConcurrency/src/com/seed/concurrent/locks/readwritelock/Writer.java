package com.seed.concurrent.locks.readwritelock;

public class Writer implements Runnable
{
	MyBean  bean ;
	public  Writer(MyBean bean)
	{
		this.bean = bean ;
	}
	
	public void run() 
	{
		for (int i = 1 ; i <= 10 ; i++)
		{	
		 bean.setData(i , "Message-"+ i +": ");
		 System.out.println(Thread.currentThread().getName() + " has set data.");
		 
		 try {
			Thread.sleep(500) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}// end for
		
		 System.out.println(Thread.currentThread().getName() + "  ends. ") ;
	} // end run
}
