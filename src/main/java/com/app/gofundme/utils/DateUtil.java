package com.app.gofundme.utils;

import java.util.Date;

public class DateUtil {
    public static int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
