package com.github.utils;

import com.github.entity.WaitingEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.github.utils.QueryParameters.QUERY;
import static com.github.utils.QueryParameters.WAITING_TIMELINE;

public class QueryDecoder {

    private static Date date;

    private static int time;

    public static WaitingEntity toWaitingTimeline(String data) {
        if (!(data.startsWith(WAITING_TIMELINE)))
            throw new IllegalArgumentException("Illegal Waiting Timeline");
        String[] array = data.split(" ");
        String dateType = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(dateType);
        String[] serviceId = array[1].split("\\.");
        String[] questionType = array[2].split("\\.");
        String responseType = array[3];
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
        if (!(data.startsWith(QUERY)))
            throw new IllegalArgumentException("Illegal Query line");
        return data.split(" ");
    }
}
