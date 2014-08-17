package com.pravila.samples.logclient;

import java.util.TimerTask;
import org.apache.log4j.Logger;

public class SendLogItems extends TimerTask {

	private static Logger logger = Logger.getLogger(SendLogItems.class);

	public SendLogItems() {
	}

	public void run() {
		
		logger.debug("this is a sample log message.");
		logger.info("this is a sample info log message.");
	}
}
