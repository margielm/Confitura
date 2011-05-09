package eu.margiel.utils;

import java.util.Date;

import org.joda.time.LocalDateTime;

public class DateUtils {
	public static String dateToString(Date date) {
		return new LocalDateTime(date).toString("HH:mm dd-MM-yyyy");
	}
}
