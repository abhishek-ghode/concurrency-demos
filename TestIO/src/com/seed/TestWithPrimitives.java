package com.seed;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestWithPrimitives {
	
	public static void writeValues(OutputStream out) throws IOException{
		DataOutputStream dOut = new DataOutputStream(out);
		
		dOut.writeLong(1L);
		dOut.writeFloat(12.34555666f);
	}
	
	static public void displayValues(InputStream in) throws IOException{
		DataInputStream dIn = new DataInputStream(in);
		
		System.out.println(dIn.readLong());
		System.out.println(dIn.readFloat());
	}
	
	public static void main(String[] args) throws IOException {
		String fileName = "test.prmt";
		
		FileOutputStream fOut = new FileOutputStream(fileName);
		writeValues(fOut);
		
		FileInputStream fIn = new FileInputStream(fileName);
		displayValues(fIn);
	}
}
