package com.github.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class WaitingEntity {

    private int[] service;

    private int[] questions;

    private String responseType;

    private Date date;

    private int time;

    public WaitingEntity() {
    }

    public WaitingEntity(int[] service, int[] questions, String responseType, Date date, int time) {
        this.service = service;
        this.questions = questions;
        this.responseType = responseType;
        this.date = date;
        this.time = time;
    }

    public int[] getService() {
        return service;
    }

    public void setService(int[] service) {
        this.service = service;
    }

    public int[] getQuestions() {
        return questions;
    }

    public void setQuestions(int[] questions) {
        this.questions = questions;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaitingEntity that = (WaitingEntity) o;
        return time == that.time && Arrays.equals(service, that.service) && Arrays.equals(questions, that.questions) && Objects.equals(responseType, that.responseType) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(responseType, date, time);
        result = 31 * result + Arrays.hashCode(service);
        result = 31 * result + Arrays.hashCode(questions);
        return result;
    }

    @Override
    public String toString() {
        return "WaitingEntity{" +
                "service=" + Arrays.toString(service) +
                ", questions=" + Arrays.toString(questions) +
                ", responseType='" + responseType + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
