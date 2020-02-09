/**
 * @author Dilshad Mustafa
 * Copyright (c) Dilshad Mustafa
 * All Rights Reserved.
 * Created 24-Dec-2018
 * File Name : 
 */

/**
Copyright (c) Dilshad Mustafa 2018. All Rights Reserved.

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

package com.dilmus.dilshad.scabiai;

import java.util.UUID;

import com.dilmus.dilshad.ref.MyActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.management.AkkaManagement;
import akka.management.cluster.bootstrap.ClusterBootstrap;

public class Node1 {

	public static void main(String... args) {

      String s = "akka.remote.artery.canonical.hostname=\"127.0.0.1\", akka.management.http.hostname=\"127.0.0.1\"";

      Config c = ConfigFactory.parseString(s).withFallback(ConfigFactory.load());
      ActorSystem system = ActorSystem.create("local-cluster", c);

      // Akka Management hosts the HTTP routes used by bootstrap
      AkkaManagement.get(system).start();

      // Starting the bootstrap process needs to be done explicitly
      ClusterBootstrap.get(system).start();

      Cluster.get(system).registerOnMemberUp(
    		  
    		  new Runnable() { 
    			  public void run() { System.out.println("Node 1 joined"); }
    		  }
    	  
      );
		
		
		
		
		

		
//		xception in thread "main" java.lang.IllegalArgumentException: Illegal [akka.discovery.akka.discovery.config.class] value or incompatible class! The implementation class MUST extend akka.discovery.SimpleServiceDiscovery and take an ExtendedActorSystem as constructor argument.
//		at akka.discovery.ServiceDiscovery.akka$discovery$ServiceDiscovery$$createServiceDiscovery(ServiceDiscovery.scala:84)

		
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		UUID uuid3 = UUID.randomUUID();
	
		System.out.println("uuid1 : " + uuid1);
		System.out.println("uuid2 : " + uuid2);
		System.out.println("uuid3 : " + uuid3);
		
		
		ActorRef myActorRef1 
		  = system.actorOf(Props.create(MyActor.class), "myactor_" + uuid1);
		ActorRef myActorRef2 
		  = system.actorOf(Props.create(MyActor.class), "myactor_" + uuid2);
		ActorRef myActorRef3 
		  = system.actorOf(Props.create(MyActor.class), "myactor_" + uuid3);
		
		
		
		myActorRef1.tell("test1", ActorRef.noSender());
		myActorRef3.tell("test1", ActorRef.noSender());
	
		ActorRef ref = system.actorFor("akka://test-system/user/myactor_" + uuid2);
		ref.tell("test1", ActorRef.noSender());
	}
	
	
}
