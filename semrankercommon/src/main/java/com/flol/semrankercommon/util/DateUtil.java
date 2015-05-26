package com.flol.semrankercommon.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getTodaysMidnight()
	{
		Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        return now.getTime();
	}

	public static Date getMidnight(Date date)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(date);
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        return now.getTime();
	}
	
	public static Date getMidnightDaysAgo(Integer daysAgo)
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -(daysAgo));
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        return now.getTime();
	}
	
	public static Date getNowMinusMillis(Integer millis)
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MILLISECOND,-millis);
		return now.getTime();
	}
	
	public static Date fromStringToDate(String data, String pattern) {
		DateFormat formatter = new SimpleDateFormat(pattern);
		Date date = null;
		if (data == null || data.matches("0*\\.0*\\.0*"))
			return null;
		try {
			date = (Date) formatter.parse(data);
		} catch (ParseException e) {
			//System.out.println(e.getMessage());
		}
		return date;
	}
	
	public static Date fromStringToDate(String data) {
		return fromStringToDate(data, "yyyy/MM/dd");
	}

}
