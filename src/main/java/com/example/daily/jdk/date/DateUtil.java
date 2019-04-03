/*
 * Copyright (c) 2017, Jiehun.com.cn Inc. All Rights Reserved
 */

package com.example.daily.jdk.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具. <br/>
 *
 * @author Zhiyuan Cui
 * @version 1.0.0
 * @date 2017年10月18日 下午3:06:27
 */
public class DateUtil {
    private DateUtil(){}
    /**
     * 缺省日期格式 yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 缺省时间格式 HH:mm:ss
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * 缺省长日期格式 yyyy-MM-dd HH:mm
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 缺省长日期格式,精确到秒 yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC2 = "yyyy/MM/dd HH:mm:ss";

    /**
     * yyyyMMddHHmm
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC3 = "yyyyMMddHHmm";

    /**
     * yyyy-MM-dd 23:59:59
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC4 = "yyyy-MM-dd 23:59:59";

    /**
     * yyyy-MM-dd HH:mm:00
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC5 = "yyyy-MM-dd HH:mm:00";

    /**
     * yyyy-MM-dd HH:00:00
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC6 = "yyyy-MM-dd HH:00:00";

    /**
     * yyyy-MM-dd 00:00:00
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC7 = "yyyy-MM-dd 00:00:00";
    /**
     * yyyyMMdd
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC8 = "yyyyMMdd";
    /**
     * EEE,dd MMM yyyy HH:mm:ss 'GMT'
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC9 = "EEE,dd MMM yyyy HH:mm:ss 'GMT'";

    /**
     * yyyy.MM.dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC10 = "yyyy.MM.dd HH:mm:ss";

    /**
     * yyyy年MM月dd日
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC11 = "yyyy年MM月dd日";

    public static final String DEFAULT_DATE_FORMAT_CHINESE_DAY = "MM月dd日";

    public static final String DEFAULT_DATE_FORMAT_DOT = "yyyy.MM.dd";

    /**
     * yyyy.M.d
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC12 = "yyyy.M.d";
    /**
     * yyyyMMddHHmmss
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC13 = "yyyyMMddHHmmss";

    /**
     * yyyy/MM/dd
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC14 = "yyyy/MM/dd";

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC15 = "yyyy/MM/dd HH:mm:ss";

    /**
     * 默认时区
     */
    public static final ZoneId DEFAULT_ZONE = ZoneId.of("Asia/Shanghai");

    /**
     * 格式化时间，和时间的精度相关
     */
    public static Date formatDate(Date date, String formatStr) {
        Date ret = null;
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String dateStr = formatter.format(date);
        try {
            ret = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 日期类型转换成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format, Locale locale) {
        String ret = "";
        if (date == null) {
            return ret;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        ret = formatter.format(date);
        return ret;
    }

    /**
     * 日期类型转换成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        String ret = "";
        if (date == null) {
            return ret;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ret = formatter.format(date);
        return ret;
    }

    /**
     * 缺省日期类型转换成字符串. yyyy-MM-dd HH:mm:ss<br/>
     *
     * @param date
     * @return
     * @author yuliqian
     * @since JDK 1.6
     */
    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_DATETIME_FORMAT_SEC);
    }

    /**
     * 传入一个时间的字符串，及其格式定义，返回一个Date
     *
     * @param dateStr
     * @return Date
     */
    public static Date strToDate(String dateStr) {
        return strToDate(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * 传入一个时间的字符串，及其格式定义，返回一个Date
     *
     * @param dateStr, String format
     * @return Date
     */
    public static Date strToDate(String dateStr, String format) {
        Date ret = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ret = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 增加天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDays(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        Date newDate = c.getTime();
        return newDate;
    }

    /**
     * 增加月份
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        Date newDate = c.getTime();
        return newDate;
    }

    /**
     * 增加秒
     *
     * @param date
     * @param ss
     * @return
     */
    public static Date addss(Date date, int ss) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, ss);
        Date newDate = c.getTime();
        return newDate;
    }

    /**
     * 获取当前时间的月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前时间的年份
     *
     * @return
     */
    public static int getNowYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * yyyy-MM-dd HH-mm，
     *
     * @param beginDate 开始时间
     * @return
     */
    public static String getSimpleTime(Date beginDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(beginDate);
    }

    /**
     * 计算两个日期间的天数
     *
     * @param statrdate
     * @param enddate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date statrdate, Date enddate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(statrdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(enddate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取指定天数的日期的开始时间
     *
     * @return
     */
    public static String getTimeBegin(int day, Date nowDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        // 时间处理
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DATE, day);
        // 格式化
        String result = format.format(calendar.getTime());
        return result;
    }

    /**
     * 获取指定天数的日期的结束时间
     *
     * @return
     */
    public static String getTimeEnd(int day, Date nowDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        // 时间处理
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DATE, day);
        // 格式化
        String result = format.format(calendar.getTime());
        return result;
    }

    public static Date today() {
        return formatDate(new Date(), DEFAULT_DATE_FORMAT);
    }

    public static Date now() {
        return formatDate(new Date(), DEFAULT_DATETIME_FORMAT_SEC);
    }

    /**
     * 计算当前时间和两个时间的关系，1表示未开始，2表示进行中，3表示已过期
     *
     * @return
     */
    public static int getTimeStatus(Date statrdate, Date enddate) {
        Date nowDate = DateUtil.formatDate(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT_SEC);
        if (nowDate.before(statrdate)) {
            return 1;
        } else if (nowDate.after(statrdate) && nowDate.before(enddate)) {
            return 2;
        } else if (nowDate.after(enddate)) {
            return 3;
        }
        return 0;
    }

    /**
     * long 转换成 date
     *
     * @param longDate
     * @return
     */
    public static Date longToDate(long longDate) {
        Date date = new Date(longDate);
        return date;
    }

    /**
     * long 转换成 date
     *
     * @param longDate
     * @return
     * @since jdk 1.8
     */
    public static LocalDateTime longToDateNew(long longDate) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longDate), DEFAULT_ZONE);
    }

    /**
     * long 转换成 String
     *
     * @param longDate
     * @return
     * @since jdk 1.8
     */
    public static String longToStringNew(long longDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT_SEC);
        return df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(longDate), DEFAULT_ZONE));
    }

    /**
     * 时间 转换成string
     *
     * @return
     * @since jdk 1.8
     */
    public static String DateToStrNew(TemporalAccessor temporal, String dateTimeFormatter) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFormatter);
        return df.format(temporal);
    }

    /**
     * long 转换成 string
     *
     * @param longDate
     * @return
     * @since jdk 1.8
     */
    public static String longToStringNew(long longDate, String dateTimeFormatter) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFormatter);
        return df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(longDate), DEFAULT_ZONE));
    }

    /**
     * 计算两个时间之间的秒数 endTime>startTime
     *
     * @param startTime
     * @param endTime
     * @since jdk 1.8
     */
    public static Long secondsBetweenNew(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    /**
     * 计算两个时间之间的分钟数 endTime>startTime
     *
     * @param startTime
     * @param endTime
     * @since jdk 1.8
     */
    public static Long minutesBetweenNew(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.MINUTES.between(startTime, endTime);
    }

    /**
     * 计算两个时间之间的小时数 endTime>startTime
     *
     * @param startTime
     * @param endTime
     * @since jdk 1.8
     */
    public static Long hoursBetweenNew(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    /**
     * 获取当天零点时间
     *
     * @return
     */
    public static Date getTodayZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前时间距未来时间的倒计时
     *
     * @param future 未来时间
     * @return 倒计时字符串
     */
    public static String getCountdownTimeString(Date future) {
        long seconds = (future.getTime() - System.currentTimeMillis()) / 1000;
        StringBuilder s = new StringBuilder();
        long days = seconds / (24 * 60 * 60);
        long hours = (seconds % (24 * 60 * 60)) / (60 * 60);
        long minutes = (seconds % (60 * 60)) / 60;
        long sec = seconds % 60;
        s.append(days).append("天").append(hours).append("小时").append(minutes).append("分").append(sec).append("秒");
        return s.toString();
    }

    /**
     * 获取当前时间距未来时间的倒计时秒
     *
     * @param future 未来时间
     * @return 倒计时字符串
     */
    public static long getCountdownTime(Date future) {
        return future == null ? 0 : (future.getTime() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 获取当前时间 年月日的时间戳
     *
     * @param date
     * @return
     */
    public static long getTimestampYMD(Date date) {
        return strToDate(dateToString(date, DEFAULT_DATE_FORMAT)).getTime();
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getBeginMon() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return formatDate(calendar.getTime(), DateUtil.DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static Date getEndMon() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return formatDate(calendar.getTime(), DateUtil.DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取一周第一天
     *
     * @return
     */
    public static Date getBeginWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return formatDate(calendar.getTime(), DateUtil.DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取一周第一天
     *
     * @return
     */
    public static Date getEndWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 0);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        Date date = new Date();
        return formatDate(calendar.getTime(), DateUtil.DEFAULT_DATE_FORMAT);
    }
}
