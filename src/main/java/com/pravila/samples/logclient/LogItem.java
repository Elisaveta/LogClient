package com.pravila.samples.logclient;

import java.io.Serializable;

public class LogItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String level;
	private Long date;
	private String className;
	private String message;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
