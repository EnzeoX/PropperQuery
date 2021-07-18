package com.github.handler;

import com.github.controller.WaitingController;
import com.github.entity.WaitingEntity;
import com.github.service.WaitingService;
import com.github.utils.AverageWaitingTime;
import com.github.utils.DateValidator;
import com.github.utils.QueryDecoder;
import com.github.utils.QueryValidation;

import java.util.ArrayList;
import java.util.List;

public class QueryHandler {

    private static WaitingController waitingController = new WaitingController(
            new WaitingService(),
            new DateValidator("dd/MM/yyyy"),
            new QueryValidation());

    public static void querySelector(ArrayList<String> data) {
        for (String s : data) {
            if (s.startsWith("C")) {
                waitingHandler(s);
            } else if (s.startsWith("D")) {
                queryLineHandler(s);
            } else {
                System.out.println("Incorrect query");
            }
        }
    }

    private static void waitingHandler(String data) {
        try {
            waitingController.saveQuery(QueryDecoder.toWaitingTimeline(data));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void queryLineHandler(String data) {
        List<WaitingEntity> entityList = waitingController.getAllQuery();
        int queryQuantity = 0;
        int avgWaitingTime = 0;
        for (WaitingEntity waitingEntity : entityList) {
            try {
                int result = AverageWaitingTime.calculateTimeline(waitingEntity, data);
                queryQuantity++;
                avgWaitingTime = (avgWaitingTime + result) / queryQuantity;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (avgWaitingTime == 0) {
            System.out.println("-");
        } else {
            System.out.println("For query " + "\"" + data + "\"" + " avg waiting time - " + avgWaitingTime + "\n");
        }
    }
}
