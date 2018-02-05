package com.leap.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :DateUtil
 */
public class DateUtil {
  public static String DEFAULT_DATE_FORMAT_0 = "HH:mm";
  public static String DEFAULT_DATE_FORMAT_1 = "yyyy/MM/dd";
  public static String DEFAULT_DATE_FORMAT_2 = "yyyy-MM-dd";
  public static String DEFAULT_DATE_FORMAT_3 = "yyyy年M月d日";
  public static String DEFAULT_DATE_FORMAT_4 = "yyyy/MM/dd EE";
  public static String DEFAULT_DATE_FORMAT_5 = "yyyy-MM-dd HH:mm";
  public static String DEFAULT_DATE_FORMAT_6 = "yyyyMMdd HH:mm";
  public static String DEFAULT_DATE_FORMAT_7 = "yyyy-MM-dd HH:mm:ss";
  public static String DEFAULT_DATE_FORMAT_8 = "yyyy/MM/dd HH:mm:ss";

  public static String format(Date date, String format) {
    if (IsEmpty.object(date))
      return null;
    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
    return sdf.format(date);
  }

  public static Date parse(String date, String format) throws ParseException {
    if (IsEmpty.object(date))
      return null;
    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
    return sdf.parse(date);
  }

}
