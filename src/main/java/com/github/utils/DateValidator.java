package com.github.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {

    private String dateFormat;

    public DateValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public boolean isValid(Date date) {
        DateFormat df = new SimpleDateFormat(this.dateFormat);
        String todayString = df.format(date);
        df.setLenient(false);
        try {
            df.parse(todayString);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
