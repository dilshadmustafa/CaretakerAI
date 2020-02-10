package lispcmd;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LispCmd {

	
	public static void main(String args[]) throws IOException, InterruptedException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		System.out.println("LispCmd started");
		while(true) {
		str = br.readLine();
		
		System.out.println("ok1" + " " + str);
		Thread.sleep(7000);
		System.out.println("ok2");
		}
		
		// catch interupted exceptio and exit app
	}
	
	
	
}
