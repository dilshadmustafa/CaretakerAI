package com.dilmus.dilshad.test;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class Test2_HBase_Client {

   public static void main(String[] args) throws IOException {

	   Configuration config = HBaseConfiguration.create();
	   Test2_HBase_Client t = new Test2_HBase_Client();
	   String path = t.getClass()
	     .getClassLoader()
	     .getResource("hbase-site.xml")
	     .getPath();
	   config.addResource(new Path(path));	   
	   
	   HBaseAdmin.available(config);


   }
}


