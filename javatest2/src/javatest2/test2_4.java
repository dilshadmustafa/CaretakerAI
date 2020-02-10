package javatest2;

import org.armedbear.lisp.scripting.AbclScriptEngineFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.script.*;

public class test2_4 {

	public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {
		long millis = System.currentTimeMillis();
	    ScriptEngineManager scriptManager = new ScriptEngineManager();
	    scriptManager.registerEngineExtension("lisp", new AbclScriptEngineFactory());
	    ScriptEngine lispEngine = scriptManager.getEngineByExtension("lisp");	
	    millis = System.currentTimeMillis() - millis;
	    System.out.println("script engine start took " + millis);
	    
		System.out.println();
		System.out.println("*package* = " + lispEngine.get("*package*"));
		Bindings bind3 = lispEngine.createBindings();
		bind3.put("someVariable", "for interpreted call");
		lispEngine.put("someVariable", "this someVariable is in lisp engine");
		System.out.println("someVariable = " + lispEngine.get("someVariable"));
		try {
			
			//Compilation!
			/*millis = System.currentTimeMillis();
			lispEngine.eval("(defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg))", bind3);
			lispEngine.eval("(slow-compiling-macro 42)", bind3);
			millis = System.currentTimeMillis() - millis;
			System.out.println("interpretation took " + millis);
			*/
			millis = System.currentTimeMillis();
			CompiledScript cs = ((Compilable) lispEngine).compile("(print (list someVariable)) (defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg)) (slow-compiling-macro 42)");
			millis = System.currentTimeMillis() - millis;
			System.out.println("compilation took " + millis);
			
			Bindings bind1 = lispEngine.createBindings();
			bind1.put("someVariable", "for call 1");
			millis = System.currentTimeMillis();
			cs.eval(bind1);
			millis = System.currentTimeMillis() - millis;
			System.out.println("evaluation took " + millis);

			Bindings bind2 = lispEngine.createBindings();
			bind2.put("someVariable", "for call 2");
			millis = System.currentTimeMillis();
			cs.eval(bind2);
			millis = System.currentTimeMillis() - millis;
			System.out.println("evaluation took " + millis);
			
			
			
			
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
		//catch (NoSuchMethodException e) {
		//	e.printStackTrace();
		//} 
		catch (ScriptException e) {
			e.printStackTrace();
		}		
		
		
	}
	
	
	
	
}
