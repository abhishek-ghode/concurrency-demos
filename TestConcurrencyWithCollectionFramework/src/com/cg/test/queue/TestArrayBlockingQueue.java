package com.cg.test.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.cg.util.Consumer;
import com.cg.util.Producer;

public class TestArrayBlockingQueue
{
    public static void main(String[] args)
    {
        BlockingQueue<String> drop = new ArrayBlockingQueue<String>(10, true);
        
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
        
    }
}