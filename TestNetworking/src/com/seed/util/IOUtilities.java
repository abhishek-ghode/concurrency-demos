package com.seed.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class IOUtilities {
	public static void writeMessage(final OutputStream out){
		
		Thread thread = new Thread(
					new Runnable() {
						
						@Override
						public void run() {
							PrintWriter pWriter = new PrintWriter(out);
							
							while(true)
								pWriter.println("This is a test message");							
						}
					}
		);
		
		thread.start();
	}
	
	public static void readMessage(final InputStream in) throws IOException{
		Thread thread = new Thread(
				new Runnable() {
					@Override
					public void run() {
						InputStreamReader iReader = new InputStreamReader(in);
						BufferedReader bReader = new BufferedReader(iReader);
						
						try {
							while(true)
								System.out.println("Message: "+ bReader.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}						
					}
				}
		);
		
		thread.start();
		
		
		
	}
}
