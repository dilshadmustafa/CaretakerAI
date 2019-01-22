/**
 * @author Dilshad Mustafa
 * Copyright (c) Dilshad Mustafa
 * All Rights Reserved.
 * Created 12-Apr-2016
 * File Name : Test_BigArray.java
 */

/**
Copyright (c) Dilshad Mustafa 2016. All Rights Reserved.

User and Developer License

1. You can use this Software for both Personal use as well as Commercial use, 
with or without paying fee to Dilshad Mustafa. Please read fully below for the 
terms and conditions. You may use this Software only if you comply fully with 
all the terms and conditions of this License. 

2. If you want to redistribute this Software, you should redistribute only the 
original source code from Dilshad Mustafa's project and/or the compiled object 
binary form of the original source code and you should ensure to bundle and 
display this original license text and Dilshad Mustafa's copyright notice along 
with it as well as in each source code file of this Software. 

3. If you want to embed this Software within your work, you should embed only the 
original source code from Dilshad Mustafa's project and/or the compiled object 
binary form of the original source code and you should ensure to bundle and display 
this original license text and Dilshad Mustafa's copyright notice along with it as 
well as in each source code file of this Software. 

4. You should not modify this Software source code and/or its compiled object binary 
form in any way.

5. You should not redistribute any modified source code of this Software and/or 
its compiled object binary form with any changes, additions, enhancements, 
updates or modifications. You should not redistribute any modified works of this 
Software. You should not create and/or redistribute any straight forward 
translation and/or implementation of this Software source code to same and/or 
another programming language, either partially or fully. You should not redistribute 
embedded modified versions of this Software source code and/or its compiled object 
binary in any form, both within as well as outside your organization, company, 
legal entity and/or individual. 

6. You should not embed any modification of this Software source code and/or its compiled 
object binary form in any way, either partially or fully.

7. Under differently named or renamed software, you should not redistribute this 
Software and/or any modified works of this Software, including its source code 
and/or its compiled object binary form. Under your name or your company name or 
your product name, you should not publish this Software, including its source code 
and/or its compiled object binary form, modified or original. 

8. You agree to use the original source code from Dilshad Mustafa's project only
and/or the compiled object binary form of the original source code.

9. You accept and agree fully to the terms and conditions of this License of this 
software product, under same software name and/or if it is renamed in future.

10. This software is created and programmed by Dilshad Mustafa and Dilshad holds the 
copyright for this Software and all its source code. You agree that you will not infringe 
or do any activity that will violate Dilshad's copyright of this software and all its 
source code.

11. The Copyright holder of this Software reserves the right to change the terms 
and conditions of this license without giving prior notice.

12. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHOR OR COPYRIGHT HOLDER
   BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
   ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.

*/

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;

package com.dilmus.test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.dilmus.dilshad.scabiai.DMHdfsStorageHandler;
import com.dilmus.dilshad.scabiai.DMStdStorageHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leansoft.bigqueue.BigArrayImpl;
//import com.leansoft.bigqueue.BigArrayImpl;
//import com.leansoft.bigqueue.IBigArray;
import com.leansoft.bigqueue.IBigArray;

/**
 * @author Dilshad Mustafa
 *
 */
public class Test_BigArray_Hdfs {

	public static void main(String args[]) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root = mapper.createObjectNode();
		root.put("name", "Developer");
		root.put("years", 10);
		
		System.out.println(mapper.writeValueAsString(root));
		root.put("years", 25);
		System.out.println(mapper.writeValueAsString(root));
		root.put("years", 25.23456789018727737);
		System.out.println(mapper.writeValueAsString(root));
	
		// use a flag like isChanged, and only in toString() generate json, assign to m_jsonString and set isChanged to false
		// in add() or put(), set isChanged to true
		
		// retrieve values
		// .get().asText(), .asInt(), etc.
		
		System.out.println(root.get("years").asDouble());

		IBigArray bigArray = null;
		try {
			// create a new big array
			DMHdfsStorageHandler storageHandler = new DMHdfsStorageHandler();
			// works DMSeaweedStorageHandler storageHandler = new DMSeaweedStorageHandler();
			// works DMSeaweedStorageHandler storageHandler = new DMSeaweedStorageHandler("localhost-8888");
 			bigArray = new BigArrayImpl("/me/teststorage", "demo", 64*1024*1024, "/home/anees/testdata/bigfile/tutorial/testlocal", storageHandler);
 			// for seaweed bigArray = new BigArrayImpl("Test_BigArray", "demo", 64*1024*1024, "/home/anees/testdata/bigfile/tutorial/testlocal", storageHandler);			
 			// works bigArray = new BigArrayImpl("", "demo", 64*1024*1024, "/home/anees/testdata/bigfile/tutorial/testlocal", storageHandler);			 
 			if (null == bigArray) {
				System.out.println("big array is null");
				
			} else {
				System.out.println("bigArray is not null");
			}
			// ensure the new big array is empty
			/*
			assertNotNull(bigArray);
			assertTrue(bigArray.isEmpty());
			assertTrue(bigArray.size() == 0);
			assertTrue(bigArray.getHeadIndex() == 0);
			assertTrue(bigArray.getTailIndex() == 0);
			*/
			// append some items into the array
			for(int i = 0; i < 10; i++) {
				String item = String.valueOf(i);
				long index = bigArray.append(item.getBytes());
				//assertTrue(i == index);
			}
			//assertTrue(bigArray.size() == 10);
			//assertTrue(bigArray.getHeadIndex() == 10);
			//assertTrue(bigArray.getTailIndex() == 0);
			
			// randomly read items in the array
			String item0 = new String(bigArray.get(0));
			System.out.println(item0);
			//assertEquals(String.valueOf(0), item0);
			
			String item3 = new String(bigArray.get(3));
			//assertEquals(String.valueOf(3), item3);
			System.out.println(item3);
			String item9 = new String(bigArray.get(9));
			//assertEquals(String.valueOf(9), item9);
			System.out.println(item9);
			
			
			long index2 = bigArray.append(mapper.writeValueAsString(root).getBytes());
			System.out.println("index : " + index2);
			String item10 = new String(bigArray.get(10));
			System.out.println(item10);
			System.out.println(bigArray.get(10));
			
			long index3 = bigArray.append(mapper.writeValueAsString(root).getBytes("UTF-8"));
			System.out.println("index : " + index3);
			String item11 = new String(bigArray.get(11));
			System.out.println(item11);
			System.out.println(bigArray.get(11));
			
			JsonNode root2 = mapper.readTree(bigArray.get(10));
			System.out.println(root2.get("name").asText());
			
			// empty the big array
			// bigArray.removeAll(); is not supported in new bigarray. Use DataPartition.deletePartition() instead
			//assertTrue(bigArray.isEmpty());
			bigArray.flushFiles();
			bigArray.close();
			//storageHandler.deleteDirIfExists("/home/anees/testdata/bigfile/tutorial/teststorage/demo");
			storageHandler.close();
			System.out.println("done");
		} catch (Exception e) {
			throw e;
		}
		
		finally {
			//bigArray.close();
		}

		
		
	}
	
	
}
