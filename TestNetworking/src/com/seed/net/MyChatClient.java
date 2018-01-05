package com.seed.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.seed.util.IOUtilities;

public class MyChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String hostName = "localhost";
		int port = 8888;
		Socket socket = new Socket(hostName,port);
		
		

		OutputStream out = socket.getOutputStream();
		IOUtilities.writeMessage(out);
		
		InputStream in = socket.getInputStream();
		IOUtilities.readMessage(in);
	}
}
