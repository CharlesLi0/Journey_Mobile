package com.example.journeyMobile.service.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    /**
     * get the string base on the date and data format
     * @param date date
     * @param format data format
     * @return return the string of date
     */
    public static String getStringFromDate(Date date,String format){
        if (date == null) return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return simpleDateFormat.format(date);

    }

    /**
     * get the date base on the string of date and data format
     * @param date string of date
     * @param format data format
     * @return return the date
     * @throws ParseException transfer from string to date will cause ParseException
     */
    public static Date getDateFromString(String date, String format) throws ParseException {
        if (date == null) return  null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return simpleDateFormat.parse(date);
    }

    /**
     * get offset month of date
     * @param date original date
     * @param monthNum the number need to be offset
     * @return return the date
     */
    public static Date getOffSetMonthOfdate(Date date, int monthNum) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.MONTH, monthNum);

        return c.getTime();
    }

    /**
     * change the dayofMonth of the date
     * @param date date
     * @param dayOfMonth dayofMonth
     * @return return date
     */
    public static Date changeDateOfDate(Date date, int dayOfMonth){
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return c.getTime();
    }

    /**
     * offset the minute of date
     * @param date date
     * @param addMintues the minute to be added
     * @return reture the date has been offset
     */
    public static Date offsetSecondOfDate(Date date, int addMintues){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int mintue = c.get(Calendar.MINUTE);
        mintue += addMintues;

        c.set(Calendar.SECOND, mintue);

        return c.getTime();

    }

    /**
     * getTimeInMillis
     * @param date date
     * @return TimeInMillis
     */
    public static long getTimeInMillis(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.getTimeInMillis();
    }

    /**
     *  check both of date is in same month and same year
     * @param date1 date
     * @param date2 date
     * @return boolean true is same , false is not same
     */
    public static boolean isSameMonthAndYear(Date date1, Date date2){
        if (date1 == null || date2 == null) return false;

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * compare the year month and date. If all are the same then return true
     * @param date1 the date1
     * @param date2 the date2
     * @return true with the same,  false with difference
     */
    public static boolean isSameDate(Date date1, Date date2) {
        if (date1  == null || date2 == null) return false;

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE);
    }

    private static void setToZeroOClock(Calendar calendar) {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    public static boolean isInThePeriod(Date startDate, Date endDate, Date target) {
        if (startDate == null || endDate == null || target == null) return false;



        try {
            int start = Integer.valueOf(getStringFromDate(startDate, "yyyyMMdd"));
            int end = Integer.valueOf(getStringFromDate(endDate, "yyyyMMdd"));
            int t = Integer.valueOf(getStringFromDate(target, "yyyyMMdd"));

            return start <= t && end >= t;

        } catch (NumberFormatException e) {
            return  false;
        }



    }

    /**
     * diff in minutes between date1 and date2
     * @param date1 date1
     * @param date2 date2
     * @return  return long data type by date1 minus date2
     */
    public static long diffInMinutes(Date date1, Date date2){
        if (date1 == null || date2 == null) return 0;

        long diffMinute = (date1.getTime() - date2.getTime()) / (1000 * 60);

        return diffMinute;
    }

}
