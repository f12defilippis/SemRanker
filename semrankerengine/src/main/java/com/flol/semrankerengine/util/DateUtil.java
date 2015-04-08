package com.flol.semrankerengine.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getTodaysMidnight()
	{
		Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        return now.getTime();
	}
}
