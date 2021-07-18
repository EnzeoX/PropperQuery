package com.github.utils;

import com.github.entity.WaitingEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class AverageWaitingTime {

    private static String[] service;

    private static String[] question;

    private static String responseType;

    private static String[] date;

    private static Date dateFrom;

    private static Date dateTo;

    public static int calculateTimeline(WaitingEntity waitingEntity, String query) {
        String pattern = "\\.";
        String[] data = QueryDecoder.fromQueryLine(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        service = data[1].split(pattern);
        question = data[2].split(pattern);
        responseType = data[3];
        date = data[4].split("-");
        if (date.length == 2) {
            try {
                dateFrom = dateFormat.parse(date[0]);
                dateTo = dateFormat.parse(date[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                dateFrom = dateFormat.parse(date[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!service[0].equals("*")) {
            int serviceLength = 0;
            serviceLength = Math.min(service.length, waitingEntity.getService().length);
            for (int p = 0; p < serviceLength; p++) {
                if (!(Integer.parseInt(service[p]) == waitingEntity.getService()[p]))
                    throw new IllegalArgumentException("Unacceptable service - " + Arrays.toString(waitingEntity.getService()));
            }
        }
        if (!question[0].equals("*")) {
            int questionLength = 0;
            questionLength = Math.min(question.length, waitingEntity.getQuestions().length);
            for (int p = 0; p < questionLength; p++) {
                if (!(Integer.parseInt(question[p]) == waitingEntity.getQuestions()[p]))
                    throw new IllegalArgumentException("Unacceptable question - " + Arrays.toString(waitingEntity.getQuestions()));
            }
        }
        if (!responseType.equals(waitingEntity.getResponseType())) {
            throw new IllegalArgumentException("Unacceptable response type");
        }
        if (date.length == 2) {
            if (!(dateFrom.before(waitingEntity.getDate()) && dateTo.after(waitingEntity.getDate()))) {
                throw new IllegalArgumentException("Unacceptable date from");
            }
        } else {
            if (!(dateFrom.after(waitingEntity.getDate()) || dateTo.before(waitingEntity.getDate()))) {
                throw new IllegalArgumentException("Unacceptable date to");
            }
        }
        return waitingEntity.getTime();
    }
}
