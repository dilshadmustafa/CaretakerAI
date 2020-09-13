package com.dilmus.dilshad.lispcmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LispCmdClient {

	public static int sendRequest(String input, String suuid, Process p, BufferedReader br, BufferedWriter bw) throws IOException {
        String ready = "DILMUS-CAI-ready";
		String ok1 = "DILMUS-CAI-ok1";
		String ok2 = "DILMUS-CAI-ok2";       		
        String line = null;
        
    	line=br.readLine();
    	System.out.println(line);
    	
    	if (line.equalsIgnoreCase(ready)) {
    		System.out.println("ready received : " + line);
    		System.out.println("input to be sent (variable name input) : " + input);
    		String noCRLFinput = replaceAll(input);
    		System.out.println("sending input (variable name noCRLFinput) : " + noCRLFinput);
    		bw.write(suuid + " " + noCRLFinput);
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
	
	static String replaceAll(String s) {
		s = s.replaceAll("\n", " ");
		s = s.replaceAll("\r", " ");
		
		return s;
		
	}
	
	static String createLispRunFile(String actoruuid) throws IOException {
		String cmd = "/usr/local/bin/sbcl </home/anees/lisptmp/lspfilesreq/req_" + actoruuid + ".lsp 1>/home/anees/lisptmp/lspfilesres/res_" + actoruuid + ".txt 2>&1";
		String lisprunfile = "/home/anees/lisptmp/lisprunscripts/lisprun_" + actoruuid + ".sh";
		File f = new File(lisprunfile);
		
		FileWriter fw = new FileWriter(f);
		fw.write(cmd);
		fw.flush();
		fw.close();
	
		return lisprunfile;
	}
	
	static Process execLispRunFile(String lisprunfile) throws IOException {
		long millis = System.currentTimeMillis();		
		System.out.println("lisprunfile : " + lisprunfile);
		Process p2 = Runtime.getRuntime().exec("sh " + lisprunfile);
		System.out.println("sbcl exec time taken : " + (System.currentTimeMillis() - millis));
		return p2;
	}
	
	static int waitAndRemove(Process p2) throws InterruptedException {
        long millis = System.currentTimeMillis();
        
		p2.waitFor(5000, TimeUnit.MILLISECONDS);
		if (p2.isAlive())
			p2.destroyForcibly();
		
        System.out.println("sbcl completion time taken : " + (System.currentTimeMillis() - millis));
		
		return 0;
	}
	
	public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {
			
	try {
		String actoruuid = "123f0bcb-d791-4909-bd16-859a96ce9c4f";
		String lisprunfile = createLispRunFile(actoruuid);
		Process p2 = execLispRunFile(lisprunfile);
		waitAndRemove(p2);         
      } catch (Exception ex) {
         System.out.println(ex);
      }	
				
	}
	
}
