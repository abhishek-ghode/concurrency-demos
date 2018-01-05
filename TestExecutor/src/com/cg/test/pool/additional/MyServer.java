package com.cg.test.pool.additional;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyServer {	
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(8080);
		Executor pool = Executors.newFixedThreadPool(7);
		while (true) {
			final Socket connection = socket.accept();
			Runnable r = new Runnable() {
				public void run() {
					System.out.println("Serviced by "+ Thread.currentThread().getName());
					System.out.println("Do something with connection" + connection);
				}
			};
			
			pool.execute(r);
		}
	}
}
