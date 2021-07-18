package com.github.controller;

import com.github.entity.WaitingEntity;
import com.github.service.WaitingService;
import com.github.utils.DateValidator;
import com.github.utils.QueryValidation;

import java.util.List;

public class WaitingController {

    private final WaitingService waitingService;

    private final DateValidator dateValidator;

    private final QueryValidation queryValidation;

    public WaitingController(WaitingService waitingService, DateValidator dateValidator, QueryValidation queryValidation) {
        this.waitingService = waitingService;
        this.dateValidator = dateValidator;
        this.queryValidation = queryValidation;
    }

    public void saveQuery(WaitingEntity data) {
        try {
            queryValidation.validate(dateValidator, data);
            waitingService.saveInfo(data);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<WaitingEntity> getAllQuery() {
        return waitingService.getInfo();
    }
}
