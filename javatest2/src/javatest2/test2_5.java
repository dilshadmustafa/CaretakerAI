package javatest2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.armedbear.lisp.scripting.AbclScriptEngineFactory;
import javax.script.*;

public class test2_5 {

	public static void main(String args[]) {
		
	    ScriptEngineManager scriptManager = new ScriptEngineManager();
	    scriptManager.registerEngineExtension("lisp", new AbclScriptEngineFactory());
	    ScriptEngine lispEngine = scriptManager.getEngineByExtension("lisp");	
		
		System.out.println();
		System.out.println("*package* = " + lispEngine.get("*package*"));

		lispEngine.put("someVariable", "abcdefgh");
		System.out.println("someVariable = " + lispEngine.get("someVariable"));
		try {
			//Interpretation (also from streams)
			lispEngine.eval("(defun hello (arg) (print (list arg someVariable)) (terpri))");
			
			//Direct function invocation
			((Invocable) lispEngine).invokeFunction("hello", "world");
			
			lispEngine.eval("(print (list someVariable))");
			//Compilation!
			/*lispEngine.eval("(defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg))");
			long millis = System.currentTimeMillis();
			lispEngine.eval("(slow-compiling-macro 42)");
			millis = System.currentTimeMillis() - millis;
			System.out.println("interpretation took " + millis);
			*/
			long millis = System.currentTimeMillis();
			CompiledScript cs = ((Compilable) lispEngine).compile("(print (list someVariable)) (defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg)) (slow-compiling-macro 42)");
			millis = System.currentTimeMillis() - millis;
			System.out.println("compilation took " + millis);
			
			millis = System.currentTimeMillis();
			cs.eval();
			millis = System.currentTimeMillis() - millis;
			System.out.println("evaluation took " + millis);

			millis = System.currentTimeMillis();
			cs.eval();
			millis = System.currentTimeMillis() - millis;
			System.out.println("evaluation took " + millis);

			//Ecc. ecc.
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}		
		
		
	}
	
	
	
	
}
