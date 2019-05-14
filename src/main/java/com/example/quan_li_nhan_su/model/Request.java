package com.example.quan_li_nhan_su.model;


import java.util.Date;

public class Request {
    int id;
    String startDay;
    String endDay;
    String reason;
    String type;
    String dayRequest;
    String feedBack;
    Float numberDate;
    String mailApprover;

    public Request(int id, String startDay, String endDay, String reason, String type, String dayRequest, String feedBack, Float numberDate, String mailApprover) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.reason = reason;
        this.type = type;
        this.dayRequest = dayRequest;
        this.feedBack = feedBack;
        this.numberDate = numberDate;
        this.mailApprover = mailApprover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDayRequest() {
        return dayRequest;
    }

    public void setDayRequest(String dayRequest) {
        this.dayRequest = dayRequest;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public Float getNumberDate() {
        return numberDate;
    }

    public void setNumberDate(Float numberDate) {
        this.numberDate = numberDate;
    }

    public String getMailApprover() {
        return mailApprover;
    }

    public void setMailApprover(String mailApprover) {
        this.mailApprover = mailApprover;
    }
}

