package com.dilmus.test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import alluxio.AlluxioURI;
import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.exception.AlluxioException;
import alluxio.exception.FileDoesNotExistException;
import alluxio.exception.FileIncompleteException;
import alluxio.exception.OpenDirectoryException;

public class Test1_FS_Client {

	public static void main(String args[]) throws FileDoesNotExistException, OpenDirectoryException, FileIncompleteException, IOException, AlluxioException {
		FileSystem fs = FileSystem.Factory.get();
		AlluxioURI path = new AlluxioURI("alluxio://192.168.2.6:19998/testfile1");
		// Open the file for reading
		FileInStream in = fs.openFile(path);
		// Read data
		byte by[] = new byte[1024];
		in.read(by);
		// Close file relinquishing the lock
		in.close();		
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(by));
		String s = null;
		while ((s = dis.readLine()) != null)
			System.out.println(s);
	}
	
}
