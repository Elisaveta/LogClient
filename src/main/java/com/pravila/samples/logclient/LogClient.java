package com.pravila.samples.logclient;

import java.util.Timer;

/**
 * 
 * 
 */
public class LogClient {

	public static void main(String[] args) {

		runTask();
	}

	public static void runTask() {
		Timer time = new Timer();
		time.schedule(new SendLogItems(), 0, 1000 * 60);
	}
}
