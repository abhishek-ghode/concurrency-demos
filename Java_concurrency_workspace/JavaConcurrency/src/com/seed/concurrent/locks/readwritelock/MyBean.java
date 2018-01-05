package com.seed.concurrent.locks.readwritelock;

//(C)Copyright 2013 SEED Infotech,  All Rights Reserved.
/**
* Name of file : MyBean.java
* 
* @since   24th September 2013 
* @author  Sucheta
* 
*  This class demonstrates use of ReentrantReadWriteLock class , with use of 
*  fairness flag as  set  to  true.
*  This flag is set to true as each thread will get fair chance.
*  This also demonstrates use of readLock()  and writeLock() methods.
*  This bean class is used by TestReadWriteLocks.java  from same package. 
*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List ;
import java.util.ArrayList ;
import java.util.HashMap ;

public class MyBean 
{
	HashMap<Integer , String>  messages = new  HashMap<Integer , String>();

	/*
	 * TODO 1  : 
	 * comment : getData() method of this class is used by the Reader  threads and 
	 *           setData() nethod of this class is used by the Writer threads.
	 *           
	 *           Reader threads need the lock for reading purpose while Writer threads need 
	 *           the lock only for writing purpose. So here  ReentrantReadWriteLock  is used 
	 *           instead of  ReentrantLock. Because with this class we can get separate readLock 
	 *           which can be used  only for reading purpose and separate writeLock used for writing 
	 *           purpose. While we get a plain lock with the class ReentrantLock.
	 */
	
	private  boolean  fairnessFlag = true ;
	
	private  final    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(fairnessFlag);
	
	private final Lock readlock = rwl.readLock();
    private final Lock writelock = rwl.writeLock();
    
    /* 
     * TODO  2 : 
     * comment : Following function is used by the Reader thread to read the data.
     *           Hence works with readLock.
     */
    
    
    public String getData(int messageNumber) 
    {
    	 try{
    	      while(messages.size() == 0) ;
                   
    	      readlock.lock();
    	      
    	      if (messages.size() >= messageNumber)
    	          return messages.get(messageNumber);
    	      else
    	    	  return ("No message ready. ");
    	     }
          finally{
        	         
        	       if (readlock != null)
        	           readlock.unlock();
        	     
        	     } // end finally
    }
 
    /* 
     * TODO  3 : 
     * comment : Following function is used by the Writer thread to write the data.
     *           Hence works with writeLock.
     */
    
    public void setData(int messageNumber , String  message) 
    {
    	 try{
  	       	   writelock.lock();
         	   messages.put(messageNumber , message); 
             }
            finally{
          	       
          	       if (writelock != null)
          	       writelock.unlock();
          	      
          	     } // end finally
     }
    
  
}
