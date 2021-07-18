package com.github.utils;

import com.github.entity.WaitingEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class AverageWaitingTime {

    private static Date dateFrom;

    private static Date dateTo;

    public static int calculateTimeline(WaitingEntity waitingEntity, String query) {
        String pattern = "\\.";
        String[] data = QueryDecoder.fromQueryLine(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String[] service = data[1].split(pattern);
        String[] question = data[2].split(pattern);
        String responseType = data[3];
        String[] date = data[4].split("-");
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
            int serviceLength = Math.min(service.length, waitingEntity.getService().length);
            for (int p = 0; p < serviceLength; p++) {
                if (!(Integer.parseInt(service[p]) == waitingEntity.getService()[p]))
                    throw new IllegalArgumentException("Unacceptable service - "
                            + Arrays.toString(waitingEntity.getService())
                            + " for query "
                            + "\"" + query + "\"");
            }
        }
        if (!question[0].equals("*")) {
            int questionLength = Math.min(question.length, waitingEntity.getQuestions().length);
            for (int p = 0; p < questionLength; p++) {
                if (!(Integer.parseInt(question[p]) == waitingEntity.getQuestions()[p]))
                    throw new IllegalArgumentException("Unacceptable question - "
                            + Arrays.toString(waitingEntity.getQuestions())
                            + " for query "
                            + "\"" + query + "\"");
            }
        }
        if (!responseType.equals(waitingEntity.getResponseType())) {
            throw new IllegalArgumentException("Unacceptable response type "
                    + waitingEntity.getResponseType()
                    + " for query "
                    + "\"" + query + "\"");
        }
        if (date.length == 2) {
            if (!(dateFrom.before(waitingEntity.getDate()) && dateTo.after(waitingEntity.getDate()))) {
                throw new IllegalArgumentException("Unacceptable \"Date from\" "
                        + waitingEntity.getDate()
                        + " for query "
                        + "\"" + query + "\"");
            }
        } else {
            if (!(dateFrom.after(waitingEntity.getDate()) || dateTo.before(waitingEntity.getDate()))) {
                throw new IllegalArgumentException("Unacceptable \"Date to\""
                        + waitingEntity.getDate()
                        + " for query "
                        + "\"" + query + "\"");
            }
        }
        return waitingEntity.getTime();
    }
}
