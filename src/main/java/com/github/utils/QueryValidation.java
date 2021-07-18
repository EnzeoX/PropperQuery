package com.github.utils;

import com.github.entity.WaitingEntity;

import static com.github.utils.QueryParameters.*;

public class QueryValidation {

    public void validate(DateValidator validator, WaitingEntity data) {
        for (int i = 0; i < data.getService().length; i++) {
            if (data.getService()[i] > SERVICE_MAP.get(i) || data.getService()[i] < 1) {
                throw new IllegalArgumentException("Illegal service");
            }
        }
        for (int i = 0; i < data.getQuestions().length; i++) {
            if (data.getQuestions()[i] > QUESTION_MAP.get(i) || data.getQuestions()[i] < 1) {
                throw new IllegalArgumentException("Illegal service");
            }
        }
        if (!(data.getResponseType().equals(RESPONSE_NEXT) || data.getResponseType().equals(RESPONSE_FIRST)))
            throw new IllegalArgumentException("Illegal response type");
        if (!validator.isValid(data.getDate()))
            throw new IllegalArgumentException("Incorrect date");
        if (data.getTime() < 0)
            throw new IllegalArgumentException("Incorrect time");
    }
}
