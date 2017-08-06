package me.stupidme.deer.util;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by allen on 17-8-6.
 */

public class TimeConvertUtil {

    public static String millis2String(long millis) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(millis);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return year + "/" + month + "/" +
                day + "    " + hour + ":" +
                minute + ":" + second + "    " +
                dayofweek;
    }

    public static long string2Millis(String time) {
        String[] contents = time.split("/\\s+:");
        int year = Integer.parseInt(contents[0]);
        int month = Integer.parseInt(contents[1]);
        int day = Integer.parseInt(contents[2]);
        int hour = Integer.parseInt(contents[3]);
        int minute = Integer.parseInt(contents[4]);
        int second = Integer.parseInt(contents[5]);
//        int dayodweek = Integer.parseInt(contents[6]);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTimeInMillis();
    }
}
