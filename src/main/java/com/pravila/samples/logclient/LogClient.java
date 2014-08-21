package com.pravila.samples.logclient;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

/**
 * @author Elisaveta Manasieva
 * @version 1.0.0
 * 
 */
public class LogClient extends TimerTask {
	private static Logger logger = Logger.getLogger(LogClient.class);

	public static void main(String[] args) {

		runTask();
	}

	public LogClient() {}

	/**
	 * method that generate scheduled logging events
	 */
	public static void runTask() {
		Timer time = new Timer();
		time.schedule(new LogClient(), 0, 1000 * 60);
	}

	@Override
	public void run() {
		logger.debug("this is a sample log message.");
		logger.info("this is a sample info log message.");
		logger.error("this is error message");
		logger.fatal("this is fatal message");
		logger.warn("this is warn message");
		logger.trace("this is trace message");
	}
}
