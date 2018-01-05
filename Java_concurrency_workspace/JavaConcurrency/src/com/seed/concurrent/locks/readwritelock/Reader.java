package com.seed.concurrent.locks.readwritelock;

public class Reader implements  Runnable
{
	MyBean  bean ;
	
	public  Reader(MyBean bean)
	{
		this.bean = bean ;
	}
	public void run() 
	{
	  for (int i = 1 ; i <= 10 ; i++)
		{
		  String str = bean.getData(i);
		  if (str == null)
			 break ;
		  System.out.println(Thread.currentThread().getName() + " Got data as = "
				 + str); 
		  
		  try {
				Thread.sleep(500) ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} // end for
	 
	 System.out.println(Thread.currentThread().getName() + "  ends. ") ;
	} // end run
}
