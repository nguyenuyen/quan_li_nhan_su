package com.example.quan_li_nhan_su.model;

import java.sql.Timestamp;
import java.util.Date;

public class Timesheet {
    int id;
    int id_user;
    Date day;
    String startDay;
    String endDay;

    public Timesheet(int id, int id_user, Date day, String startDay, String endDay) {
        this.id = id;
        this.id_user = id_user;
        this.day = day;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public Timesheet(int id, Date day, String startDay, String endDay) {
        this.id = id;
        this.day = day;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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
}
