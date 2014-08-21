package com.pravila.samples.logclient.appender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import com.google.gson.Gson;
import com.pravila.samples.logclient.LogItem;

/**
 * @author Elisaveta Manasieva
 * 
 * @version 1.0.0
 * 
 */
public class CustomRemoteAppender extends AppenderSkeleton {

	private static Logger logger = Logger.getLogger(CustomRemoteAppender.class);

	static StringWriter stack = new StringWriter();
	private static Properties configProp = new Properties();
	InputStream in = null;

	public CustomRemoteAppender() {
		in = CustomRemoteAppender.class.getClass().getResourceAsStream(
				"/config.properties");
		try {
			configProp.load(in);
			in.close();
		} catch (IOException e) {
			logger.error("Caught IOException " + stack.toString());
		}
	}

	public void close() {
		// TODO Auto-generated method stub

	}

	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * method which receive logging events
	 * 
	 * @param event
	 * @throws IOException
	 * receive all logging events, create json object and send
	 * request to log server
	 */

	@Override
	protected void append(LoggingEvent event) {

		LogItem item = new LogItem();
		item.setLevel(event.getLevel().toString());
		item.setDate(event.getTimeStamp());
		item.setClassName(event.getLogger().getName());
		item.setMessage(event.getMessage().toString());
		Gson gson = new Gson();
		try {
			sendRequest(gson.toJson(item));
		} catch (IOException e) {
			e.printStackTrace(new PrintWriter(stack));
			logger.error("Caught IOException exception " + stack.toString());
		}

	}

	/**
	 * method which send event requests to log server to store in database
	 * 
	 * @param generatedJSONString
	 * @throws IOException
	 */

	public static void sendRequest(String generatedJSONString)
			throws IOException {

		HttpURLConnection conn = null;
		URL url = null;
		try {
			url = new URL(configProp.getProperty("host") + ":"
					+ configProp.getProperty("port") + "/json/log/post");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(
					conn.getOutputStream());
			writer.write(generatedJSONString);
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			writer.close();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace(new PrintWriter(stack));
			logger.error("Caught IOException exception "
					+ stack.toString());
		} finally {
			conn.disconnect();
		}
	}

}
