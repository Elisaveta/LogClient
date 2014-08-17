package com.pravila.samples.logclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.google.gson.Gson;

public class CustomRemoteAppender extends AppenderSkeleton {

	List<LoggingEvent> buffer = new ArrayList<LoggingEvent>();

	public void close() {
		// TODO Auto-generated method stub

	}

	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void append(LoggingEvent event) {

		LogItem item = new LogItem();
		item.setLevel(event.getLevel().toString());
		item.setDate(event.getTimeStamp());
		item.setClassName(event.getLogger().getName());
		item.setMessage(event.getMessage().toString());
		Gson gson = new Gson();
		System.out.println(gson.toJson(item));
		try {
			sendRequest(gson.toJson(item));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void sendRequest(String generatedJSONString)
			throws IOException {
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://127.0.0.1:8001/json/log/post");
			 conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			// conn.setRequestProperty ("Authorization", encodedCredentials);

			OutputStreamWriter writer = new OutputStreamWriter(
					conn.getOutputStream());

			writer.write(generatedJSONString);
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			writer.close();
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		finally {
			conn.disconnect();
		}

	}

}
