package com.pravila.samples.logclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

@SuppressWarnings("deprecation")
public class TestMain {

		

	 public static void main(String[] args) {
		 String input = "{\"level\":\"DEBUG\",\"date\":1408273292931,\"className\":\"main\",\"message\":\"this is a sample log message.\"}";
		    HttpClient client = new DefaultHttpClient();
		    HttpPost post = new HttpPost("http://127.0.0.1:8001/json/log/post");
		    try {
		      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		      nameValuePairs.add(new BasicNameValuePair("jsonString",
		          input));
		      post.setEntity(new StringEntity(input));
		 
		      HttpResponse response = client.execute(post);
		      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		      String line = "";
		      while ((line = rd.readLine()) != null) {
		        System.out.println(line);
		      }
		      
		      client.getConnectionManager().shutdown();

		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

}
