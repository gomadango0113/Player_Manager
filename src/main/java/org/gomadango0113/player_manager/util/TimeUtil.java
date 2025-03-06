package org.gomadango0113.player_manager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TimeUtil {

    public static boolean isFormat(String time, String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            simple.parse(time);

            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }

    public static Date toParse(String time, String format) throws ParseException {
        if (isFormat(time, format)) {
            SimpleDateFormat simple = new SimpleDateFormat(format);

            return simple.parse(time);
        }

        return null;
    }

    public static Date localToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime dateToLocal(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}
