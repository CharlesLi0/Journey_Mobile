package com.example.journeyMobile.service.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtils {

    private static final String TIME_FORMAT = "h:mm a";

    public static String getFormattedTime(int hourOfDay, int minute) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getDayOfWeek(int year, int month, int dayOfMonth) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getMonth(int year, int month, int dayOfMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        return simpleDateFormat.format(calendar.getTime());
    }
}
