package com.example.quan_li_nhan_su.model;

import java.sql.Timestamp;
import java.util.Date;

public class Timesheet {
    int id;
    int id_user;
    Date day;
    String check_in;
    String check_out;
    int history;

    public Timesheet(int id, int id_user, Date day, String check_in, String check_out) {
        this.id = id;
        this.id_user = id_user;
        this.day = day;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public Timesheet(int id, Date day, String check_in, String check_out, int history ) {
        this.id = id;
        this.day = day;
        this.check_in = check_in;
        this.check_out = check_out;
        this.history = history;
    }

    public Timesheet(Date day, String check_in, String check_out) {
        this.day = day;
        this.check_in = check_in;
        this.check_out = check_out;
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

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public int getHistory() { return history; }

    public void setHistory(int history) { this.history = history; }
}
