package com.gmail.generationdotz2.c_observer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
    public String convertUTCToHumanReadableDate(String lastUpdate) {
        long lastUpdatedMillis = Long.parseLong(lastUpdate);
        String date = new SimpleDateFormat("MMM dd, yyyy hh:mm a").format(new Date(lastUpdatedMillis * 1000));
        return date;
    }
}
