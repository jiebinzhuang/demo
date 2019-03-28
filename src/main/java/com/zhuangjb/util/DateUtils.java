package com.zhuangjb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 提供日期操作的静态函数
 * 
 * @author watson chen
 */
public class DateUtils {
	/**
	 * 日期格式：yyyyMMdd
	 */
	public static String DATEFORMAT_8CHAR = "yyyyMMdd";

	/**
	 * 日期格式：yyyy.MM.dd
	 */
	public static String DATEFORMAT_DOT = "yyyy.MM.dd";

	/**
	 * 日期格式：yyyy.MM.dd HH:mm:ss
	 */
	public static String DATATIME_FORMAT_DOT = "yyyy.MM.dd HH:mm:ss";

	/**
	 * 日期格式：yyyy/MM/dd
	 */
	public static String DATEFORMAT_SLASH = "yyyy/MM/dd";

	/**
	 * 日期格式：yyyy/MM/dd HH:mm:ss
	 */
	public static String DATETIME_FORMAT_SLASH = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 日期格式：yyyy-MM-dd
	 */
	public static String DATEFORMAT_HORIZONTAL = "yyyy-MM-dd";

	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String DATETIME_FORMAT_HORIZONTAL = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式：yyyyMMddHHmmss
	 */
	public static String DATE_TIME_FORMAT_ALL = "yyyyMMddHHmmss";

	/**
	 * 日期格式：HH:mm:ss
	 */
	public static String DATETIME_TIMER = "HH:mm:ss";

	/**
	 * 将日期字符串或时间转换成时间类型 日期字符串中的日期分隔符可是是"/",".","-"，返回时间具体到秒
	 * 
	 * @param dateString
	 * @return Date
	 */
	public static Date parseString(String dateString) {

		if (dateString == null || "".equals(dateString) || "null".equals(dateString.toLowerCase())) {
			// System.err.println("The date string is null!");
			return null;
		}

		try {
			dateString = dateString.trim();
			boolean isCommon = true;
			if (dateString.indexOf("-") != -1) {
				dateString = dateString.replaceAll("-", "/");
			} else if (dateString.indexOf(".") != -1) {
				dateString = dateString.replace('.', '/');
			} else if (dateString.indexOf("/") == -1)
				isCommon = false;

			String fmt = "yyyy/MM/dd";

			if (dateString.indexOf(":") != -1) {
				if (isCommon)
					fmt = "yyyy/MM/dd HH:mm:ss";
				else
					fmt = "yyyyMMdd HH:mm:ss";
			} else {
				if (!isCommon)
					fmt = "yyyyMMdd";
			}

			DateFormat df = new SimpleDateFormat(fmt);
			return df.parse(dateString);

		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * 将日期类型转换成字符串 其格式包括:通过DateFormat转换的标准格式，如yyyy/MM/dd HH:mm:ss或者yyyy-MM-dd
	 * HH:mm:ss 非标准的格式，如YYYY/MM/DD
	 * 
	 * @param dt
	 * @param fmt
	 * @return String
	 */
	public static String formatDate(Date dt, String fmt) {
		if (dt == null) {
			return null;
		}
		try {
			DateFormat df = new SimpleDateFormat(fmt);
			return df.format(dt);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将日期类型格式化成字符串，格式为：yyyy-MM-dd 2011-5-10
	 * 
	 * @param dt
	 */
	public static String formatDate(Date dt) {
		return formatDate(dt, DateUtils.DATEFORMAT_HORIZONTAL);
	}

	/**
	 * 转换日期格式 其格式包括:通过DateFormat转换的标准格式，如yyyy/MM/dd HH:mm:ss或者yyyy-MM-dd
	 * HH:mm:ss 非标准的格式，如YYYY/MM/DD
	 * 
	 * @param dt
	 * @param fmt
	 * @return String
	 */
	public static String formatDate(String dt, String fmt) {
		if (dt == null) {
			return null;
		}
		try {
			DateFormat df = new SimpleDateFormat(fmt);
			return df.format(parseString(dt));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取当前操作系统的日期
	 * 
	 * @return Date
	 */
	public static java.sql.Date getSqlDate() {
		try {
			return new java.sql.Date((new java.util.Date()).getTime());
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * 以yyyy-MM-dd格式返回给定日期
	 * 
	 * @param date
	 * @return Date
	 */
	public static java.sql.Date getSqlDate(Date date) {
		try {
			return new java.sql.Date(date.getTime());
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * 以yyyy-MM-dd格式返回dateString指定的日期
	 * 
	 * @param dateString
	 * @return java.sql.Date
	 */
	public static java.sql.Date getSqlDate(String dateString) {
		try {
			return new java.sql.Date(parseString(dateString).getTime());
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * 获取当前系统日期,格式如yyyy-MM-dd
	 * 
	 * @return 当期日期(格式:yyyy-MM-dd)
	 */
	public static String getCurrentDate() {
		return DateUtils.formatDate(new Date(), DATEFORMAT_HORIZONTAL);
	}

	/**
	 * 获取当期系统日期，并按传入参数进行格式化
	 * 
	 * @param dateFormat
	 *            格式化
	 * @return 当期日期（格式按照传入的dateFormat）
	 */
	public static String getCurrentDate(String dateFormat) {
		return DateUtils.formatDate(new Date(), dateFormat);
	}

	/**
	 * 获取当前操作系统的时间
	 * 
	 * @return Date 当前操作系统的时间
	 */
	public static Date getNowTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 返回给定日期增加millisecond毫秒后的日期
	 * 
	 * @param dt
	 * @param millisecond
	 * @return Date
	 */
	public static Date addMillisecond(Date dt, int millisecond) {
		return addSecond(dt, (long) millisecond);
	}

	/**
	 * 返回给定日期增加millisecond毫秒后的日期
	 * 
	 * @param dt
	 * @param millisecond
	 * @return Date
	 */
	public static Date addMillisecond(Date dt, long millisecond) {
		Date result = new Date();
		result.setTime(dt.getTime() + millisecond);
		return result;
	}

	/**
	 * 返回给定日期增加second秒后的日期
	 * 
	 * @param dt
	 * @param second
	 * @return Date
	 */
	public static Date addSecond(Date dt, int second) {
		return addSecond(dt, (long) second);
	}

	/**
	 * 返回给定日期增加second秒后的日期
	 * 
	 * @param dt
	 * @param second
	 * @return Date
	 */
	public static Date addSecond(Date dt, float second) {
		return addSecond(dt, (double) second);
	}

	/**
	 * 返回给定日期增加second秒后的日期
	 * 
	 * @param dt
	 * @param second
	 * @return Date
	 */
	public static Date addSecond(Date dt, long second) {
		return addMillisecond(dt, 1000L * second);
	}

	/**
	 * 返回给定日期增加second秒后的日期
	 * 
	 * @param dt
	 * @param second
	 * @return Date
	 */
	public static Date addSecond(Date dt, double second) {
		Double millisecond = new Double(1000.0 * second);
		return addMillisecond(dt, millisecond.longValue());
	}

	/**
	 * 返回给定日期增加minute分钟后的日期
	 * 
	 * @param dt
	 * @param minute
	 * @return Date
	 */
	public static Date addMinute(Date dt, int minute) {
		return addMinute(dt, (long) minute);
	}

	/**
	 * 返回给定日期增加minute分钟后的日期
	 * 
	 * @param dt
	 * @param minute
	 * @return Date
	 */
	public static Date addMinute(Date dt, float minute) {
		return addMinute(dt, (double) minute);
	}

	/**
	 * 返回给定日期增加minute分钟后的日期
	 * 
	 * @param dt
	 * @param minute
	 * @return Date
	 */
	public static Date addMinute(Date dt, long minute) {
		return addMillisecond(dt, 1000L * 60L * minute);
	}

	/**
	 * 返回给定日期增加minute分钟后的日期
	 * 
	 * @param dt
	 * @param minute
	 * @return Date
	 */
	public static Date addMinute(Date dt, double minute) {
		Double millisecond = new Double(1000.0 * 60.0 * minute);
		return addMillisecond(dt, millisecond.longValue());
	}

	/**
	 * 返回给定日期增加hour小时后的日期
	 * 
	 * @param dt
	 * @param hour
	 * @return Date
	 */
	public static Date addHour(Date dt, int hour) {
		return addHour(dt, (long) hour);
	}

	/**
	 * 返回给定日期增加hour小时后的日期
	 * 
	 * @param dt
	 * @param hour
	 * @return Date
	 */
	public static Date addHour(Date dt, float hour) {
		return addHour(dt, (double) hour);
	}

	/**
	 * 返回给定日期增加hour小时后的日期
	 * 
	 * @param dt
	 * @param hour
	 * @return Date
	 */
	public static Date addHour(Date dt, long hour) {
		return addMillisecond(dt, 1000L * 60L * 60L * hour);
	}

	/**
	 * 返回给定日期增加hour小时后的日期
	 * 
	 * @param dt
	 * @param hour
	 * @return Date
	 */
	public static Date addHour(Date dt, double hour) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * hour);
		return addMillisecond(dt, millisecond.longValue());
	}

	/**
	 * 返回给定日期增加day天后的日期
	 * 
	 * @param dt
	 * @param day
	 * @return Date
	 */
	public static Date addDay(Date dt, int day) {
		return addDay(dt, (long) day);
	}

	/**
	 * 返回给定日期增加day天后的日期
	 * 
	 * @param dt
	 * @param day
	 * @return Date
	 */
	public static Date addDay(Date dt, float day) {
		return addDay(dt, (double) day);
	}

	/**
	 * 返回给定日期增加day天后的日期
	 * 
	 * @param dt
	 * @param day
	 * @return Date
	 */
	public static Date addDay(Date dt, long day) {
		return addMillisecond(dt, 1000L * 60L * 60L * 24L * day);
	}

	/**
	 * 返回给定日期增加day天的日期
	 * 
	 * @param dt
	 * @param day
	 * @return Date
	 */
	public static Date addDay(Date dt, double day) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * 24.0 * day);
		return addMillisecond(dt, millisecond.longValue());
	}

	/**
	 * 返回给定日期增加month个月后的日期
	 * 
	 * @param dt
	 * @param month
	 * @return Date
	 */
	public static Date addMonth(Date dt, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
		return cal.getTime();
	}

	/**
	 * 返回给定日期增加month个月后的日期
	 * 
	 * @param dt
	 * @param month
	 * @return Date
	 */
	public static Date addMonth(Date dt, float month) {
		return addMonth(dt, (double) month);
	}

	/**
	 * 返回给定日期增加month个月后的日期
	 * 
	 * @param dt
	 * @param month
	 * @return Date
	 */
	public static Date addMonth(Date dt, long month) {
		return addMonth(dt, (new Long(month)).intValue());
	}

	/**
	 * 在给定日期基础上增加month个月
	 * 
	 * @param dt
	 * @param month
	 * @return Date
	 */
	public static Date addMonth(Date dt, double month) {
		double floorMonth = Math.floor(month);
		double decimalMonth = month - floorMonth;
		dt = addMonth(dt, (new Double(floorMonth)).intValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		Date nextdt = cal.getTime();
		long monthMillisecond = nextdt.getTime() - dt.getTime();
		double millisecond = monthMillisecond * decimalMonth;
		return addMillisecond(dt, (long) millisecond);
	}

	/**
	 * 在给定时间的增加year年份
	 * 
	 * @param dt
	 * @param year
	 * @return Date 增加年份后的时间
	 */
	public static Date addYear(Date dt, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + year);
		return cal.getTime();
	}

	/**
	 * 返回当前时间的年份
	 * 
	 * @return int
	 */
	public static Integer getYear() {
		GregorianCalendar currentDay = new GregorianCalendar();
		return currentDay.get(Calendar.YEAR);
	}

	/**
	 * 得到给定时间的年份
	 * 
	 * @param dateStr
	 * @return int
	 */
	public static Integer getYear(String dateStr) {
		GregorianCalendar currentDay = new GregorianCalendar();
		Date date = parseString(dateStr);
		currentDay.setTime(date);
		return currentDay.get(Calendar.YEAR);
	}

	/**
	 * 得到给定时间的年份
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getYear(Date date) {
		GregorianCalendar currentDay = new GregorianCalendar();
		currentDay.setTime(date);
		return currentDay.get(Calendar.YEAR);
	}

	/**
	 * 得到当然日期的月份
	 * 
	 * @return int
	 */
	public static Integer getMonth() {
		GregorianCalendar currentDay = new GregorianCalendar();
		return currentDay.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到给定时间的月份
	 * 
	 * @param dateStr
	 * @return int
	 */
	public static Integer getMonth(String dateStr) {
		GregorianCalendar currentDay = new GregorianCalendar();
		Date date = parseString(dateStr);
		currentDay.setTime(date);
		return currentDay.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到给定时间的月份
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getMonth(Date date) {
		GregorianCalendar currentDay = new GregorianCalendar();
		currentDay.setTime(date);
		return currentDay.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当然日期的天
	 * 
	 * @return int
	 */
	public static Integer getDay() {
		GregorianCalendar currentDay = new GregorianCalendar();
		return currentDay.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到给定时间的天
	 * 
	 * @param dateStr
	 * @return int
	 */
	public static Integer getDay(String dateStr) {
		GregorianCalendar currentDay = new GregorianCalendar();
		Date date = parseString(dateStr);
		currentDay.setTime(date);
		return currentDay.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到给定时间的天
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getDay(Date date) {
		GregorianCalendar currentDay = new GregorianCalendar();
		currentDay.setTime(date);
		return currentDay.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到当前时间的小时数
	 * 
	 * @return int
	 */
	public static Integer getHour() {
		GregorianCalendar currentDay = new GregorianCalendar();
		return currentDay.get(Calendar.HOUR);
	}

	/**
	 * 得到当前时间的分钟数
	 * 
	 * @return int
	 */
	public static Integer getMinute() {
		GregorianCalendar currentDay = new GregorianCalendar();
		return currentDay.get(Calendar.MINUTE);
	}

	/**
	 * 得到当前时间的秒数
	 * 
	 * @return int
	 */
	public static Integer getSecond() {
		GregorianCalendar currentDay = new GregorianCalendar();
		return currentDay.get(Calendar.SECOND);
	}

	/**
	 * 返回给定日期增加year年后的日期
	 * 
	 * @param dt
	 * @param year
	 * @return Date
	 */
	public static Date addYear(Date dt, float year) {
		return addYear(dt, (double) year);
	}

	/**
	 * 返回给定日期增加year年后的日期
	 * 
	 * @param dt
	 * @param year
	 * @return Date
	 */
	public static Date addYear(Date dt, long year) {
		return addYear(dt, (new Long(year)).intValue());
	}

	/**
	 * 在给定的日期上做年的加减运算
	 * 
	 * @param dt
	 * @param year
	 * @return Date
	 */
	public static Date addYear(Date dt, double year) {
		double floorYear = Math.floor(year);
		double decimalYear = year - floorYear;
		dt = addYear(dt, (new Double(floorYear)).intValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		Date nextdt = cal.getTime();
		long yearMillisecond = nextdt.getTime() - dt.getTime();
		double millisecond = yearMillisecond * decimalYear;
		return addSecond(dt, (long) millisecond);
	}

	/**
	 * 获取两个时间间隔的周数
	 * 
	 * @param floorDate
	 * @param goalDate
	 * @return double
	 */
	public static double getIntervalWeeks(Date floorDate, Date goalDate) {
		return getIntervalHours(floorDate, goalDate) / (7 * 24);
	}

	/**
	 * 获取两个时间间隔的天数
	 * 
	 * @param floorDate
	 * @param goalDate
	 * @return double
	 */
	public static double getIntervalDays(Date floorDate, Date goalDate) {
		return getIntervalHours(floorDate, goalDate) / (24);
	}

	/**
	 * 获取两个时间的间隔天数
	 * 
	 * @param floorDate
	 * @param goalDate
	 * @return double
	 */
	public static double getIntervalDays(String floorDate, String goalDate) {
		Date dt1 = parseString(floorDate);
		Date dt2 = parseString(goalDate);
		return getIntervalHours(dt1, dt2) / (24);
	}

	/**
	 * 获取两个时间间隔的小时数
	 * 
	 * @param floorDate
	 *            Date
	 * @param goalDate
	 *            Date
	 * @return double
	 */
	public static double getIntervalHours(String floorDate, String goalDate) {
		Date dt1 = parseString(floorDate);
		Date dt2 = parseString(goalDate);
		return getIntervalHours(dt1, dt2);
	}

	/**
	 * 获取两个时间间隔的小时数
	 * 
	 * @param floorDate
	 *            Date
	 * @param goalDate
	 *            Date
	 * @return double
	 */
	public static double getIntervalHours(Date floorDate, Date goalDate) {
		double intervalHours = 0;
		long milliseconds = getIntervalMillSeconds(floorDate, goalDate);
		intervalHours = milliseconds / (3600f * 1000);
		return intervalHours;
	}

	/**
	 * 获取两时间的间隔分钟数
	 * 
	 * @param floorDate
	 *            Date
	 * @param goalDate
	 *            Date
	 * @return double
	 */
	public static double getIntervalMinutes(Date floorDate, Date goalDate) {
		double intervalMinutes = 0;
		long milliseconds = getIntervalMillSeconds(floorDate, goalDate);
		intervalMinutes = milliseconds / (60f * 1000);
		return intervalMinutes;
	}

	/**
	 * 获取两时间间隔的毫秒数
	 * 
	 * @param floorDate
	 *            Date
	 * @param goalDate
	 *            Date
	 * @return double
	 */
	public static long getIntervalMillSeconds(String floorDate, String goalDate) {
		Date dt1 = parseString(floorDate);
		Date dt2 = parseString(goalDate);
		return getIntervalMillSeconds(dt1, dt2);
	}

	/**
	 * 获取两时间间隔的毫秒数
	 * 
	 * @param floorDate
	 *            Date
	 * @param goalDate
	 *            Date
	 * @return double
	 */
	public static long getIntervalMillSeconds(Date floorDate, Date goalDate) {
		long milliseconds = goalDate.getTime() - floorDate.getTime();
		return milliseconds;
	}

	/**
	 * 获取两个时间之间的间隔并以格式:xx天xx小时xx分钟xx秒输出
	 * 
	 * @param floorDate
	 *            Date
	 * @param goalDate
	 *            Date
	 * @return String
	 */
	public static String getIntervalDateStr(Date floorDate, Date goalDate) {
		if (floorDate == null || goalDate == null)
			throw new IllegalArgumentException("获取两时间间隔时输入时间不能为空!");
		StringBuffer intervalDate = new StringBuffer();
		long intervalSeconds = getIntervalMillSeconds(floorDate, goalDate) / 1000;
		if (intervalSeconds < 0)
			intervalSeconds = -intervalSeconds;
		long days;
		long hours;
		long minuts;

		days = intervalSeconds / (24 * 60 * 60);
		intervalSeconds = intervalSeconds - days * 24 * 60 * 60;
		hours = intervalSeconds / (60 * 60);
		intervalSeconds = intervalSeconds - hours * 3600;
		minuts = intervalSeconds / 60;
		intervalSeconds = intervalSeconds - minuts * 60;
		if (days > 0)
			intervalDate.append(days + "天");
		if (hours > 0)
			intervalDate.append(hours + "小时");
		if (minuts > 0)
			intervalDate.append(minuts + "分钟");
		if (intervalSeconds > 0)
			intervalDate.append(intervalSeconds + "秒");

		return intervalDate.toString();
	}

	/**
	 * 根据日期返回星期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getWeekNameOfDate(int week) {
		String weekstr = null;
		switch (week) {
		case Calendar.MONDAY:
			weekstr = "星期一";
			break;
		case Calendar.TUESDAY:
			weekstr = "星期二";
			break;
		case Calendar.WEDNESDAY:
			weekstr = "星期三";
			break;
		case Calendar.THURSDAY:
			weekstr = "星期四";
			break;
		case Calendar.FRIDAY:
			weekstr = "星期五";
			break;
		case Calendar.SATURDAY:
			weekstr = "星期六";
			break;
		case Calendar.SUNDAY:
			weekstr = "星期日";
			break;
		default:
			weekstr = "未知";
			break;
		}
		return weekstr;
	}

	/**
	 * 根据日期返回星期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getWeekNameOfDate(String dateStr) {
		int week = getWeekOfDate(dateStr);
		return getWeekNameOfDate(week);
	}

	/**
	 * 根据日期返回星期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getWeekNameOfDate(Date date) {
		int week = getWeekOfDate(date);
		return getWeekNameOfDate(week);
	}

	/**
	 * 返回给定日期说对应的星期,注意日期格式必须是2011-07-27
	 * 
	 * @param y
	 *            年
	 * @param m
	 *            月
	 * @param d
	 *            日
	 * @return
	 */
	public static Integer getWeekOfDate(String dateStr) {
		Date date = parseString(dateStr);
		return getWeekOfDate(date);
	}

	public static Integer getWeekOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 返回当前月的第一天
	 * 
	 * @return
	 */
	public static String getFirstDateOfMonth() {
		String date = DateUtils.getCurrentDate();
		date = date.substring(0, 7);
		date += "-01";
		return date;
	}

	/**
	 * 返回指定日期所在月份的第一天，如输入2011-07-26，返回2011-07-01
	 * 
	 * @return
	 */
	public static String getFirstDateOfMonth(String date) {
		date = date.substring(0, 7);
		date += "-01";
		return date;
	}

	/**
	 * 返回当前周的周一
	 * 
	 * @return
	 */
	public static String getMondayOfWeek() {
		String firstDay = getCurrentDate();
		int weekday = getWeekOfDate(firstDay);// 周几
		if (weekday != Calendar.MONDAY) {
			if (weekday == Calendar.SUNDAY) {// 周日，向前推6天
				firstDay = addDay(getSqlDate(getCurrentDate()), -6).toString();
			} else {
				firstDay = addDay(getSqlDate(getCurrentDate()), weekday - 1).toString();
			}
		}
		return firstDay;
	}

	/**
	 * 返回指定日期所在周的第一天（周一），如输入2012-07-05，返回2012-07-02 周一
	 * 
	 * @return
	 */
	public static String getMondayOfWeek(String date) {
		String firstDay = date;
		int weekday = getWeekOfDate(firstDay);// 周几
		if (weekday != Calendar.MONDAY) {
			if (weekday == Calendar.SUNDAY) {// 周日，向前推6天
				firstDay = addDay(getSqlDate(date), -6).toString();
			} else {
				firstDay = addDay(getSqlDate(date), weekday - 1).toString();
			}
		}
		return firstDay;
	}

	/**
	 * 返回上个月的今天
	 * 
	 * @return
	 * @author xuqc
	 * @date 2012-5-16
	 * 
	 */
	public static String getLastMonthDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		return DateUtils.formatDate(cal.getTime(), DateUtils.DATEFORMAT_HORIZONTAL);
	}

	/**
	 * 返回当前月的最后一天
	 * 
	 * @return
	 */
	public static String getLastDateOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return DateUtils.formatDate(cal.getTime(), DateUtils.DATEFORMAT_HORIZONTAL);
	}

	/**
	 * 返回指定日期所在月份的最后一天，如输入2011-07-26，返回2011-07-31
	 * 
	 * @return
	 */
	public static String getLastDateOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.parseString(date));
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return DateUtils.formatDate(cal.getTime(), DateUtils.DATEFORMAT_HORIZONTAL);
	}

	public static String getLastDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return DateUtils.formatDate(cal.getTime(), DateUtils.DATEFORMAT_HORIZONTAL);
	}

	/**
	 * 获取日期范围 例子：假定当前日期是2011-09-05 1:2011-09-01 3:2011-07-01 6:2011-04-01
	 * before6:2011-03-31
	 * 
	 * @param scope
	 * @return
	 */
	public static String getDateScope(String scope) {
		String dateStr = null;
		int amount = 0;
		String amountStr = scope;

		if (scope != null) {
			if (scope.startsWith("before")) {
				amountStr = scope.substring(6, scope.length());
			}
			amount = 0 - Integer.parseInt(amountStr) + 1;

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, amount);
			if (scope.startsWith("before")) {
				// 月份再减一
				cal.add(Calendar.MONTH, -1);
			}

			dateStr = DateUtils.formatDate(cal.getTime(), "yyyy-MM");

			if (scope.startsWith("before")) {
				dateStr += "-31";
			} else {
				dateStr += "-01";
			}
		}

		return dateStr;
	}

	public static String getTS() {
		return formatDate(new Date(), DATETIME_FORMAT_HORIZONTAL);
	}

	/**
	 * 庄接滨 add FIXME
	 * 
	 * @return
	 */
	public static String getSysTime() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
		Date date = new Date();
		return format.format(date);
	}

	/**
	 * 获取下月第一天
	 * 
	 * @return
	 */
	public static Date nextMonthFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(getLastMonthDate());
		// System.out.println(getLastDateOfMonth());
		// System.out.println(getDateScope("1"));
		// System.out.println(getDateScope("3"));
		// System.out.println(getDateScope("6"));
		// System.out.println(getDateScope("6+"));

		/*
		 * PropertyConfigurator.configure("log4j.properties");
		 * EventLog.setLoggerType(EventLog.LOG4J); //default log4j
		 * EventLog.setLogLevel("error"); //default INFO
		 * EventLog.setWriteFile("true"); //default false
		 * EventLog.setLogpath("E:/workspace/PersonalBank/log/"); String aa =
		 * DateUtil.formatDate(new Date(),DATETIME_FORMAT_HORIZONTAL); aa =
		 * DateUtil.formatDate(new Date(),DATEFORMAT_HORIZONTAL); aa =
		 * DateUtil.formatDate(new Date(),DATETIME_TIMER); aa =
		 * DateUtil.formatDate(new Date(),DATEFORMAT_SLASH);
		 */
		// System.out.println(DateUtil.parseString("12/11/1984"));
		// String aa = DateUtil.formatDate(new Date(),"dd-MMM-yyyy");
		// System.err.println(aa);
		// //System.out.println("noe:"+DateUtil.addHour(new Date(), 10));
		//
		// DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
		// Date date = df.parse("11-Oct-2008");

		// System.out.println(DateUtils.formatDate(new Date(),
		// DATETIME_FORMAT_HORIZONTAL));
		// System.out.println(DateUtils.getNowTime());

		/*
		 * System.err.println("20070100".substring(0,4));
		 * System.err.println(DateUtil.formatDate("2007/09/10",
		 * DateUtil.DATEFORMAT_8CHAR)); GregorianCalendar currentDay = new
		 * GregorianCalendar(); int today=currentDay.get(Calendar.DAY_OF_MONTH);
		 * int month=currentDay.get(Calendar.MONTH); int year=
		 * currentDay.get(Calendar.YEAR);
		 * System.err.println(DateUtil.getYear());
		 * System.err.println(DateUtil.getMonth());
		 * System.err.println(DateUtil.getHour());
		 * System.err.println(DateUtil.getMinute());
		 * System.err.println(DateUtil.getSecond());
		 * System.err.println("2006".substring(2));
		 */

		/*
		 * String dateStr = DateUtil.formatDate(DateUtil.getNowTime(), "YYYY");
		 * System.err.println(dateStr);
		 */

		/*
		 * Date floorDate = DateUtil.parseString("2005-09-26 15:24:50"); Date
		 * goalDate = DateUtil.parseString("2005-09-26 15:25:50"); System.err
		 * .println(DateUtil.formatDate(DateUtil.getNowTime(), "yyMMdd")); long
		 * tmp = 20051117001L; tmp = tmp << 3; System.err.println(tmp);
		 * System.err.println(DateUtil.getNowTime().toString());
		 * System.err.println(new
		 * Time(DateUtil.parseString("2006-05-01 12:00:00").getTime()));
		 * System.err.println(new
		 * Timestamp(DateUtil.parseString("2006-05-01 12:00:00").getTime()));
		 */
		// System.err.println(DateUtil.parseString("20061210 12:20:30"));
		// System.out.println(DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		// System.out.println(DateUtil.getSqlDate("2004.3.31 12:11:01"));

	}
}