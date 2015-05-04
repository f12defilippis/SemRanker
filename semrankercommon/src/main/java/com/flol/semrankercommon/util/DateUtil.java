package com.flol.semrankercommon.util;

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

}
