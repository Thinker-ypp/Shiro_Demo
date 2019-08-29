package com.zdh.frame.shiro.web.utils;

import com.zdh.frame.shiro.common.enums.CommonErrors;
import com.zdh.frame.shiro.common.exception.BaseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类。
 *
 * @author yupanpan
 * @version 1.0.0
 * @since JKD 1.8
 */
public class DateTimeUtils {
	
	/**
	 * 格式化日期返回一个字符串日期，如果参数日期为null返回null值。
	 * 
	 * @param date
	 *            日期对象。
	 * 
	 * @param pattern
	 *            格式化类型字符串。
	 * 
	 * @return 一个字符串日期。
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		} else {
			Date fdata = date;
			if (date instanceof java.sql.Date) {
				fdata = (Date) date;
			}
			SimpleDateFormat formater = new SimpleDateFormat(pattern);
			String ret = formater.format(fdata);
			return ret;
		}
	}

    /**
     * 创建一个日期对象。
     *
     * @param year       年。
     * @param month      月（从1开始表示第一个月）。
     * @param dayOfMonth 天。
     * @return 一个日期对象。
     */
    public static final java.sql.Date createDate(int year, int month, int dayOfMonth) {
        LocalDate ldate = createLocalDate(year, month, dayOfMonth);
        return java.sql.Date.valueOf(ldate);
    }

    /**
     * 创建一个日期时间对象。
     *
     * @param year       年。
     * @param month      月（从1开始表示第一个月）。
     * @param dayOfMonth 天。
     * @param hour       小时。
     * @param minute     分。
     * @param second     秒 。
     * @return 一个日期时间对象。
     */
    public static final Date createDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        LocalDateTime ldatetime = createLocalDateTime(year, month, dayOfMonth, hour, minute, second);
        return toDate(ldatetime);
    }

    /**
     * 创建一个日期对象。
     *
     * @param year       年。
     * @param month      月（从1开始表示第一个月）。
     * @param dayOfMonth 天。
     * @return 一个日期对象。
     */
    public static final LocalDate createLocalDate(int year, int month, int dayOfMonth) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        return date;
    }

    /**
     * 创建一个日期时间对象。
     *
     * @param year       年。
     * @param month      月（从1开始表示第一个月）。
     * @param dayOfMonth 天。
     * @param hour       小时。
     * @param minute     分。
     * @param second     秒 。
     * @return 一个日期时间对象。
     */
    public static final LocalDateTime createLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        LocalDateTime datetime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return datetime;
    }

    /**
     * 比较二个日期对象的日期是否相同。
     *
     * @param date1 日期对象1。
     * @param date2 日期对象2。
     * @return 真表示二个日期对象的日期相同。
     */
    public static final boolean equals(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            LocalDate ldate1 = LocalDate.parse(new java.sql.Date(date1.getTime()).toString());
            LocalDate ldate2 = LocalDate.parse(new java.sql.Date(date2.getTime()).toString());
            return ldate1.isEqual(ldate2);
        }
        return false;
    }

    /**
     * @return 系统当前日期时间。
     */
    public static final Date now() {
        return toDate(LocalDateTime.now());
    }

    /**
     * @return 系统当前日期。
     */
    public static final java.sql.Date nowOfDate() {
        LocalDate date = LocalDate.now();
        return java.sql.Date.valueOf(date);
    }

    /**
     * @return 系统当前日期月份中的第几天。
     */
    public static final int nowOfDayOfMonth() {
        LocalDate date = LocalDate.now();
        return date.get(ChronoField.DAY_OF_MONTH);
    }

    /**
     * @return 系统当前日期周中的星期几（1表示星期一，7表示星期日）。
     */
    public static final int nowOfDayOfWeek() {
        LocalDate date = LocalDate.now();
        return date.get(ChronoField.DAY_OF_WEEK);
    }

    /**
     * @return 系统当前日期一年中第几天。
     */
    public static final int nowOfDayOfYear() {
        LocalDate date = LocalDate.now();
        return date.get(ChronoField.DAY_OF_YEAR);
    }

    /**
     * @return 系统当前日期年份中第几个月（1表示第一个月，12表示最后一个月）。
     */
    public static final int nowOfMonthOfYear() {
        LocalDate date = LocalDate.now();
        return date.get(ChronoField.MONTH_OF_YEAR);
    }

    /**
     * @return 系统当前日期的年份。
     */
    public static final int nowOfYear() {
        LocalDate date = LocalDate.now();
        return date.get(ChronoField.YEAR);
    }

    /**
     * 将本地日期时间对象（LocalDateTime）转换成日期时间对象（java.util.Date）。
     *
     * @param ldatetime 本地日期时间对象。
     * @return 一个日期时间对象。
     */
    public static final Date toDate(LocalDateTime ldatetime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = ldatetime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 将字符日期解析成日期对象。
     *
     * @param date 字符日期。
     * @return 一个日期对象。
     */
    public static final Date parse2Date(String date){
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date.indexOf(" ") == -1) {
            try {
                sdf.applyPattern("yyyy-MM-dd");
                return sdf.parse(date);
            } catch (ParseException e1) {
                sdf.applyPattern("yyyy/MM/dd");
                try {
                    return sdf.parse(date);
                } catch (ParseException e2) {
                    sdf.applyPattern("yyyyMMdd");
                    try {
                        return sdf.parse(date);
                    } catch (ParseException e3) {
                        throw new BaseException(CommonErrors.DATE_PARSE_ERROR.getCode(), CommonErrors.DATE_PARSE_ERROR.getMessage());
                    }
                }
            }
        } else {
            try {
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
                return sdf.parse(date);
            } catch (ParseException e1) {
                sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
                try {
                    return sdf.parse(date);
                } catch (ParseException e2) {
                    sdf.applyPattern("yyyyMMdd HH:mm:ss");
                    try {
                        return sdf.parse(date);
                    } catch (ParseException e3) {
                        throw new BaseException(CommonErrors.DATE_PARSE_ERROR.getCode(), CommonErrors.DATE_PARSE_ERROR.getMessage());
                    }
                }
            }
        }
    }

    public static final String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String str = "";
        if (date != null) {
            String time = df.format(date);
            str = time.substring(0, 4) + "年" + time.substring(4, 6) + "月" + time.substring(6, 8) + "日";
        }
        return str;
    }

    public static String dateToYyyyMMDDHHmm(Date date) {
        if (date == null) {
            return "";
        }else {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        }
    }
    public static String dateToYyyyMMDDHHmmpoint(Date date) {
        if (date == null)
            return "";
        else {
            return new SimpleDateFormat("yyyy.MM.dd HH:mm").format(date);
        }
    }

    public static String dateToYyyyMMDD(Date date) {
        if (date == null) {
            return "";
        }else {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
    }
    /**
     *     获取30分钟之后的时间
     */
    public static Date get30Min(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 30);
        return cal.getTime();
    }
}
