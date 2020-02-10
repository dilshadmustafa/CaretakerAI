package javatest2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class test2_6 {

	public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {
		
		
	try {
         ProcessBuilder pb = new ProcessBuilder("java", "-jar", "/home/anees/Downloads/abcl-bin-1.6.0/abcl.jar");
         final Process p=pb.start();
         BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
         String line;
         Thread.sleep(10000);
         
         /* dont immediately read
          * while((line=br.readLine())!=null){
            System.out.println(line);
         }*/
         
         BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

         String str2 = "(+ 23 45)";
         
         bw.write(str2);
         //bw.newLine();
         bw.flush();
         
         Thread.sleep(10000);
         while((line=br.readLine())!=null){
             System.out.println(line);
          }                 
         
         
         
         //System.out.println(p.isAlive());
         String str = "(defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg)) (slow-compiling-macro 42)";
         bw.newLine();
         bw.write(str);
         bw.newLine();
         bw.flush();
         
         Thread.sleep(10000);
         while((line=br.readLine())!=null){
             System.out.println(line);
          }
         
         Thread.sleep(10000);
         while((line=br.readLine())!=null){
             System.out.println(line);
          }
           
         
         
         
         
         /*BufferedReader br2=new BufferedReader(new InputStreamReader(p.getErrorStream()));
         while((line=br2.readLine())!=null){
             System.out.println(line);
          }*/
         
 
         
         
      } catch (Exception ex) {
         System.out.println(ex);
      }	
		
		
		
		
		
		
		
		
		
		CompletableFuture<String> completableFuture
		  = CompletableFuture.supplyAsync(() -> "Testing");
		 
		CompletableFuture<Void> future = completableFuture
		  .thenAccept(s -> System.out.println("Computation returned: " + s));
		 
		//future.get();	
		future.get(10, TimeUnit.SECONDS);
		if (!future.isDone())
			future.cancel(true);
		
		
	}
	
}
