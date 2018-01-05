package com.cg.test.future;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile): ");
		String keyword = in.nextLine();

		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread thread = new Thread(task);
		thread.start();
		
		System.out.println("Searching for keyword ....");
		try {
			System.out.println(task.get() + " matching files.");
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
		}
		
		
	}
}

