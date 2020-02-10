package javatest2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class test2_2 {

	public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {
		
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
