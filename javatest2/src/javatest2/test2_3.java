package javatest2;

import org.armedbear.lisp.scripting.AbclScriptEngineFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.script.*;

public class test2_3 {

	public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {
		
	    ScriptEngineManager scriptManager = new ScriptEngineManager();
	    scriptManager.registerEngineExtension("lisp", new AbclScriptEngineFactory());
	    ScriptEngine lispEngine = scriptManager.getEngineByExtension("lisp");	
		
		System.out.println();
		System.out.println("*package* = " + lispEngine.get("*package*"));
		Object someValue = new String("asdf");
		lispEngine.put("someVariable", someValue);
		System.out.println("someVariable = " + lispEngine.get("someVariable"));
		try {
			lispEngine.eval("(print (list someVariable))");
			lispEngine.eval("(defun hello (arg) (print (list arg someVariable)) (terpri))");
			//Direct function invocation
			((Invocable) lispEngine).invokeFunction("hello", "world");

			CompletableFuture<String> completableFuture
			  = CompletableFuture.supplyAsync(() -> "Testing");
			 
			CompletableFuture<Void> future = completableFuture
			  .thenAccept(s -> System.out.println("Computation returned: " + s));
			 
			//future.get();	
			future.get(10, TimeUnit.SECONDS);
			if (!future.isDone())
				future.cancel(true);		
			
			

			//Ecc. ecc.
		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
		catch (ScriptException e) {
			e.printStackTrace();
		}		
		
		
	}
	
	
	
	
}
