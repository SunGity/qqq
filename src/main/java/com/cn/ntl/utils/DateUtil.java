package com.cn.ntl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
 * 
 * @ClassName: DateUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author ljt 
 * @date 2018-11-16 上午8:43:01 * *
 */

public class DateUtil {

	public static String DATE_PATTERN_YMD = "yyyy-MM-dd";
	public static String DATE_PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";

	private static Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * 获取当前日期(年、月、日)
	 * 
	 * @param dateType
	 * @return
	 */
	public static int getCurrentDate(String dateType) {
		int date = 0;
		Calendar calendar = Calendar.getInstance();
		if (dateType != null && dateType.equals("YEAR")) {
			date = calendar.get(Calendar.YEAR);
		} else if (dateType != null && dateType.equals("MONTH")) {
			date = calendar.get(Calendar.MONTH) + 1;
		} else if (dateType != null && dateType.equals("DAY")) {
			date = calendar.get(Calendar.DATE);
		}
		return date;
	}

	/**
	 * 获取多少天之前日期
	 * 
	 * @param beforDay
	 * @return
	 */
	public static String getBeforDate(int beforDay, String pattern) {
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		long currTime = System.currentTimeMillis();
		long time = currTime / 1000 - 24 * 60 * 60 * beforDay;
		String str = DateUtil.toString(time, pattern);
		return str;
	}

	/**
	 * 生成主键 得到带年月日流水号
	 * 
	 * @return
	 */
	public String toTimeStamp() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		if (date != null) {
			date = date + String.format("%04d", new Random().nextInt(10000));
		}
		return date;
	}

	public static String toOrderTimeStamp() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String date = df.format(new Date());

		return date;
	}

	/**
	 * 生成主键 得到带年月日流水号
	 * 
	 * @return
	 */
	public Long getYmdSequnce() {
		DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
		String date = df.format(new Date());
		if (date != null) {
			date = date + String.format("%04d", new Random().nextInt(10000));
		}
		return Long.valueOf(date);
	}

	/**
	 * 获取指定时间n小时之前的时间
	 */
	public static Date getHoursAgoTime(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - n);
		return cal.getTime();
	}

	/**
	 * 获取指定时间n分钟之前的时间
	 */
	public static Date getMinutesAgoTime(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - n);
		return cal.getTime();
	}

	public static String stringFormatString(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = dateFormat.parse(date);
		String date2 = dateFormat.format(date1);
		return date2;
	}

	/**
	 * 将一个字符串转换成日期格式
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if (("" + date).equals("")) {
			return null;
		}
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newDate;
	}

	/**
	 * 把日期转换成字符串型
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}

	public static String toString(Long time, String pattern) {
		if (time > 0) {
			if (time.toString().length() == 10) {
				time = time * 1000;
			}
			Date date = new Date(time);
			String str = DateUtil.toString(date, pattern);
			return str;
		}
		return "";
	}

	/**
	 * 获取上个月的开始结束时间
	 * 
	 * @return
	 */
	public static String[] getLastMonth() {
		// 取得系统当前时间
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// 取得系统当前时间所在月第一天时间对象
		cal.set(Calendar.DAY_OF_MONTH, 1);

		// 日期减一,取得上月最后一天时间对象
		cal.add(Calendar.DAY_OF_MONTH, -1);

		// 输出上月最后一天日期
		int day = cal.get(Calendar.DAY_OF_MONTH);

		String months = "";
		String days = "";

		if (month > 1) {
			month--;
		} else {
			year--;
			month = 12;
		}
		if (!(String.valueOf(month).length() > 1)) {
			months = "0" + month;
		} else {
			months = String.valueOf(month);
		}
		if (!(String.valueOf(day).length() > 1)) {
			days = "0" + day;
		} else {
			days = String.valueOf(day);
		}
		String firstDay = "" + year + "-" + months + "-01";
		String lastDay = "" + year + "-" + months + "-" + days;

		String[] lastMonth = new String[2];
		lastMonth[0] = firstDay;
		lastMonth[1] = lastDay;

		// System.out.println(lastMonth[0] + "||" + lastMonth[1]);
		return lastMonth;
	}

	/**
	 * 获取当月的开始结束时间
	 * 
	 * @return
	 */
	public static String[] getCurrentMonth() {
		// 取得系统当前时间
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// 取得系统当前时间所在月第一天时间对象
		cal.set(Calendar.DAY_OF_MONTH, 1);

		// 日期减一,取得上月最后一天时间对象
		cal.add(Calendar.DAY_OF_MONTH, -1);

		// 输出上月最后一天日期
		int day = cal.get(Calendar.DAY_OF_MONTH);

		String months = "";
		String days = "";

		if (!(String.valueOf(month).length() > 1)) {
			months = "0" + month;
		} else {
			months = String.valueOf(month);
		}
		if (!(String.valueOf(day).length() > 1)) {
			days = "0" + day;
		} else {
			days = String.valueOf(day);
		}
		String firstDay = "" + year + "-" + months + "-01";
		String lastDay = "" + year + "-" + months + "-" + days;

		String[] currentMonth = new String[2];
		currentMonth[0] = firstDay;
		currentMonth[1] = lastDay;

		// System.out.println(lastMonth[0] + "||" + lastMonth[1]);
		return currentMonth;
	}

	public static int getDateline() {

		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 返回10位当前时间戳
	 * 
	 * @return
	 */
	public static long getDatelineLong() {

		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 返回某时刻的13时间戳
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static long getDatelineLong(String date, String pattern) {
		// Date date1 = toDate(date, pattern);
		// Date date2 = toDate("1970-01-01", "yyyy-MM-dd");
		// long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime()
		// - date2.getTime() : date2.getTime() - date1.getTime();
		return toDate(date, pattern).getTime();
	}

	public static int getDatelineForTime(String date) {
		return (int) (toDate(date, "yyyy-MM-dd HH:mm:ss").getTime() / 1000);
	}

	public static int getDateline(String date) {
		return (int) (toDate(date, "yyyy-MM-dd").getTime() / 1000);
	}

	public static int getDateLong(String date) {
		return (int) (toDate(date, "yyyy-MM").getTime() / 1000);
	}

	public static int getDateline(String date, String pattern) {
		return (int) (toDate(date, pattern).getTime() / 1000);
	}

	/**
	 * 获取时间戳 日期范围：2011-09-01 08:00:00到当前 时间范围为8-23点
	 * 
	 * @return
	 */
	public static long getRandomTimeMillis() {
		long s = DateUtil.toDate("2011-09-01 08:00:00", "yyyy-MM-dd HH:mm:ss")
				.getTime();
		long e = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			long t = (long) (Math.random() * (e - s));
			Long r = s + t;
			String str = DateUtil.toString(r, "HH");
			int h = Integer.valueOf(str);
			if (h > 8 && h < 23) {
				// System.out.println(DateUtil.toString(r,
				// "yyyy-MM-dd HH:mm:ss"));
				return r.longValue();
			}
		}
		long day = (long) (10 * Math.random());
		return e - day * 24 * 60 * 60 * 1000;
	}

	/**
	 * 检查当前时间是否包含在区间内
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean containsCurrTime(Long startTime, Long endTime) {
		if (startTime == null || endTime == null) {
			return false;
		}
		Long curr = DateUtil.getDatelineLong();
		if (curr.longValue() > startTime.longValue()
				&& curr.longValue() < endTime.longValue())
			return true;
		return false;
	}

	/**
	 * 检查当前时间是否包含在区间内
	 * 
	 * @param startTime
	 * @param endTime
	 * @return 0 时间区间错误 1 在区间内 2 当前时间小于开始区间值 3 当前时间大于结束区间值
	 */
	public static int CurrTimeStatus(Long startTime, Long endTime) {
		if (startTime == null || endTime == null) {
			return 0;
		}
		Long curr = DateUtil.getDatelineLong();
		if (curr.longValue() > startTime.longValue()
				&& curr.longValue() < endTime.longValue())
			return 1;
		else if (curr.longValue() < startTime.longValue())
			return 2;
		else if (curr.longValue() > endTime.longValue())
			return 3;
		return 0;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 74. * 增加日期中某类型的某数值。如增加日期 75. * @param date 日期 76. * @param dateType 类型
	 * 77. * @param amount 数值 78. * @return 计算后日期 79.
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/**
	 * 220. * 返回日期加X月后的日期 221.
	 */
	public static Date addMonth(Date date, int amount) {
		try {
			return addInteger(date, Calendar.MONTH, amount);
		} catch (Exception e) {
			log.debug("DateUtil.addMonth():" + e.toString());
			return null;
		}
	}

	// 返回距当前时间前后n分钟时间
	public static String addDateMinut(int x) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date date = new Date();
		if (date == null)
			return "";
		System.out.println("front:" + format.format(date)); // 显示输入的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, x);// 24小时制
		date = cal.getTime();
		System.out.println("after:" + format.format(date)); // 显示更新后的日期
		cal = null;
		return format.format(date);

	}

	public static String getDataTime() {
		String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSSS";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DATE_TIME_FORMAT);
		String time = simpleDateFormat.format(new Date(System
				.currentTimeMillis()));
		return time;
	}

	public static void main(String[] args) {
		System.out.println(addDateMinut(-60));

		/*
		 * long d= 1319990400 ; d=d*1000; int line =getDateline("2011-10-31");
		 * 
		 * System.out.println( line + "--"+toString(new Date(d), "yyyy-MM-dd"));
		 * System.out.println(d);
		 */

		// int d1 =getDateline("2011-10-30");
		// int d2 =getDateline("2011-10-15");
		//
		// System.out.println(d1);
		// System.out.println(d2);
		//
		// int f = 15 *24*60*60;
		//
		// System.out.println(d1-f);

		// System.out.println( new Date(1320205608000l));
		// System.out.println( DateUtil.toString( new
		// Date(1320205608000l),"yyyy-MM-dd HH:mm:ss"));
		// System.out.println(DateUtil.toString(DateUtil.getRandomTimeMillis(),"yyyy-MM-dd HH:mm:ss"));

		// int start = DateUtil.getDateline("2013-05-01", "yyyy-MM-dd");
		// System.out.println(start);
		// System.out.println(System.currentTimeMillis());

		/*
		 * System.out.println(DateUtil.getDatelineLong("2013-07-01",
		 * "yyyy-MM-dd"));
		 * System.out.println(DateUtil.getDatelineLong("2013-07-31",
		 * "yyyy-MM-dd")); System.out.println(System.currentTimeMillis());
		 * System.out.println(DateUtil.getDatelineLong());
		 * System.out.println(DateUtil.getDateline());
		 */

	}
}
