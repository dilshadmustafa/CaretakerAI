package javatest2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class test2_7 {

	public static int sendRequest(String input, Process p, BufferedReader br, BufferedWriter bw) throws IOException {
        String ready = "DILMUS-CAI-ready";
		String ok1 = "DILMUS-CAI-ok1";
		String ok2 = "DILMUS-CAI-ok2";       		
        String line = null;
        
    	line=br.readLine();
    	System.out.println(line);
    	
    	if (line.equalsIgnoreCase(ready)) {
            bw.write(input);
            bw.newLine();
            bw.flush();        		
    	}

        //System.out.println("p4");	
    	line=br.readLine();
    	System.out.println(line);
    	
    	if (line.contains(ok1)) {
    		System.out.println("ok1 received : " + line);
    		return 0;
    	} else
    		return -1;
          
	}
	
	public static int receiveResponse(Process p, BufferedReader br, BufferedWriter bw) throws IOException {
        String ready = "DILMUS-CAI-ready";
		String ok1 = "DILMUS-CAI-ok1";
		String ok2 = "DILMUS-CAI-ok2";       
        String line = null;
        
    	line=br.readLine();
    	System.out.println(line);
    	
    	if (line.contains(ok2)) {
    		System.out.println("ok2 received : " + line);
    		return 0;
    	} else
    		return -1;
        //System.out.println("p4");	

	}
	
	public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {
		
		
	try {
         ProcessBuilder pb = new ProcessBuilder("java", "-cp", 
        		 "/home/anees/Downloads/abcl-bin-1.6.0/abcl.jar:/home/anees/allprjs/CaretakerAI/lispcmd/bin/",
        		 "lispcmd.LispCmd");
         pb.redirectErrorStream(true);
         pb.environment().put("user.dir", "/home/anees/allprjs/CaretakerAI/lispcmd/bin/");
         //System.out.println("p1");
         final Process p=pb.start();
         //System.out.println("p2");
         Thread.sleep(10000);
         
         BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
         BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

         sendRequest("(+ 23 45)", p, br, bw);
         receiveResponse(p, br, bw);
         
         /*
         String line = null;
         
         line=br.readLine();
         System.out.println(line);
         
         //System.out.println("p4");
         String str2 = "testing";
         bw.write(str2);
         bw.newLine();
         bw.flush();
         //System.out.println("p3");
         
         line=br.readLine();
         System.out.println(line);
         line=br.readLine();
         System.out.println(line);

         //System.out.println("p5");
         */
         /*
         Thread.sleep(10000);
         while((line=br.readLine())!=null){
             System.out.println(line);
          }                 
          */
         
         System.out.println(p.isAlive());
         
 
         
         
      } catch (Exception ex) {
         System.out.println(ex);
      }	
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
