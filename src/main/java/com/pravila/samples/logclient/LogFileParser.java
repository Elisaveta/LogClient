package com.pravila.samples.logclient;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class LogFileParser {

	public static List<LogItem> retriveLogItems(String fileLocation) {
		List<LogItem> result = new ArrayList<LogItem>();
		File file = new File(fileLocation);
		try {
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			for (String line : lines) {

				LogItem item = new LogItem();
				String[] lineColumns = line.split("-");
				item.setLevel(lineColumns[0]);
				item.setDate(parseLogDate(lineColumns[1]));
				item.setClassName(lineColumns[2]);

				item.setMessage(lineColumns[3]);

				result.add(item);
			}
			//FileUtils.writeStringToFile(file, "");

		} catch (IOException e) {
			// TODO do something

		}

		return result;
	}

	private static Long parseLogDate(String stringDate) {
		Long miliseconds = 0L;

		// 2014/08/16 02:12:38,317
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss,SSS");
		try {
			Date date = sdf.parse(stringDate);
			miliseconds = date.getTime();
		} catch (ParseException e) {
			// TODO Auto/generated catch block
			e.printStackTrace();
		}
		return miliseconds;
	}

}
