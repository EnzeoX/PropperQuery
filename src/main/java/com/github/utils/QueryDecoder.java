package com.github.utils;

import com.github.entity.WaitingEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QueryDecoder {

    private static String[] serviceId;

    private static String[] questionType;

    private static String responseType;

    private static Date date;

    private static int time;

    public static WaitingEntity toWaitingTimeline(String data) {
        if (!data.startsWith("C"))
            throw new IllegalArgumentException("Illegal Waiting Timeline");
        String[] array = data.split(" ");
        String dateType = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(dateType);
        serviceId = array[1].split("\\.");
        questionType = array[2].split("\\.");
        responseType = array[3];
        try {
            date = format.parse(array[4].replaceAll("\\.", "/"));
            time = Integer.parseInt(array[5]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new WaitingEntity(
                Arrays.stream(serviceId).mapToInt(Integer::parseInt).toArray(),
                Arrays.stream(questionType).mapToInt(Integer::parseInt).toArray(),
                responseType,
                date,
                time
        );
    }

    public static String[] fromQueryLine(String data) {
        if (!data.startsWith("D"))
            throw new IllegalArgumentException("Illegal Query line");
        return data.split(" ");
    }
}
