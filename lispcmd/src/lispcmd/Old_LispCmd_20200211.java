package lispcmd;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.armedbear.lisp.scripting.AbclScriptEngineFactory;

public class Old_LispCmd_20200211 {

	
	public static void test1(String args[]) throws IOException, InterruptedException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		System.out.println("ready");
		while(true) {
		str = br.readLine();
		
		System.out.println("ok1" + " " + str);
		Thread.sleep(7000);
		System.out.println("ok2");
		System.out.println("ready");
		}
		
		// catch interupted exceptio and exit app
	}
	
	public static void main(String args[]) throws IOException, InterruptedException {
		
        String ready = "DILMUS-CAI-ready";
		String ok1 = "DILMUS-CAI-ok1";
		String ok2 = "DILMUS-CAI-ok2";       
 		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
	    ScriptEngineManager scriptManager = new ScriptEngineManager();
	    scriptManager.registerEngineExtension("lisp", new AbclScriptEngineFactory());
	    ScriptEngine lispEngine = scriptManager.getEngineByExtension("lisp");	
		
		try {
		System.out.println(ready);
		while(true) {
		str = br.readLine();
		
		System.out.println(ok1 + " " + str);
		
		//---------------------------------------------
		// processing section
		// do not output to stdout
		Object obj = lispEngine.eval(str);
		System.out.println(ok2 + " " + obj);
		
		//------------------------------------------------
		
		//Thread.sleep(7000);
		//System.out.println(ok2);
		System.out.println(ready);
		}
		} 
		catch (ScriptException e) {
			e.printStackTrace();
		}		

		// catch interupted exception and exit app
	}
	
	
	
	
	
	
	
	
}
