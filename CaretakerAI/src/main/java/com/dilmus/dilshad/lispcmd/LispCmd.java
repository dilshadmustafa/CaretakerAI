package com.dilmus.dilshad.lispcmd;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.armedbear.lisp.scripting.AbclScriptEngineFactory;

public class LispCmd {

	
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
	
	static String replaceAll(String s) {
		s = s.replaceAll("\n", " ");
		s = s.replaceAll("\r", " ");
		
		return s;
		
	}
	
	public static void main(String args[]) {
		
        String ready = "DILMUS-CAI-ready";
		String ok1 = "DILMUS-CAI-ok1";
		String ok2 = "DILMUS-CAI-ok2";       
 		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
	    ScriptEngineManager scriptManager = new ScriptEngineManager();
	    scriptManager.registerEngineExtension("lisp", new AbclScriptEngineFactory());
	    ScriptEngine lispEngine = scriptManager.getEngineByExtension("lisp");	
		
	    UUID uuid = UUID.randomUUID();
	    int uuidlen = uuid.toString().length();

	    System.out.println(ready);

		while(true) {
	    
	    
		try {
		//DEBUG SECTION-------------------
		str = br.readLine();
		//str = "adb19b1d-0ad0-4766-8f51-12451b842e57 (defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg)) (slow-compiling-macro 42)";
		//--------------------------------
		System.out.println(ok1 + " " + str);
		
		//PROCESSING SECTION---------------
		// do not output to stdout
		
		//4e177d29-bcf4-4a32-9c1f-e81dc584e06e
		String suuid = str.substring(0, uuidlen);
		String str2 = str.substring(uuidlen, str.length());
		
		File f = new File("/home/anees/lisptmp/res_" + suuid + ".txt");
		FileWriter fw = new FileWriter(f);
		
		lispEngine.getContext().setWriter(fw);
		Object obj = lispEngine.eval(str2);
		
		fw.close();
		//---------------------------------
		
		//DEBUG SECTION-------------
		//lispEngine.eval(str2);
		//System.out.println("actual output  : " + obj.toString());
		//--------------------------
		String s = obj.toString();
		s = replaceAll(s);
		System.out.println(ok2 + " " + s);
		System.out.println(ready);
		}
		catch (ScriptException e) {
			String ex = replaceAll(e.toString());
			System.out.println(ok2 + " " + ex);
			System.out.println(ready);

		}
//		catch (InterruptedException e) {
//			System.exit(0);
//		}
		catch (Exception e) {
			String ex = replaceAll(e.toString());
			System.out.println(ok2 + " " + ex);
			System.out.println(ready);
			
		}
		catch (Error e) {
			String ex = replaceAll(e.toString());
			System.out.println(ok2 + " " + ex);
			System.out.println(ready);
			
		}
		// catch interupted exception and exit app
		//DEBUG SECTION-------------
		//break;
		//--------------------------
		}
		
	} 
	
}
