package com.seed.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.seed.util.IOUtilities;

public class MyChatService {
	
	void m1()
	{
		System.out.println(MyChatService.this);;;;;;;;;;;;
		
		while(true);
		
//		{
//			System.out.println("inside infinite loop");
//		}
	}
	
	public static void main(String[] args) throws IOException {
//		listen on a port (start a service)
		new MyChatService().m1();
		int port = 8888;
		ServerSocket service = new ServerSocket(port);
	
		
		
		System.out.println();
		
//		wait for a client's request
		System.out.println("Waiting for client's connection...");
		Socket socket =  service.accept();
		
		System.out.println("Client is now connected!");
		
		InputStream in = socket.getInputStream();
		IOUtilities.readMessage(in);

		OutputStream out = socket.getOutputStream();
		IOUtilities.writeMessage(out);
		
		
	}
}
