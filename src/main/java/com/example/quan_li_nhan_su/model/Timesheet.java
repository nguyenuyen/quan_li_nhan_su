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
    String request_checkin;
    String request_checkout;
    String reason;
    String feedback;
    String mail;
    String name;
    int id_department;

    public Timesheet(Date day, String check_in, String check_out, String mail, String name, int id_department) {
        this.day = day;
        this.check_in = check_in;
        this.check_out = check_out;
        this.mail = mail;
        this.name = name;
        this.id_department = id_department;
    }

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

    public Timesheet(int id, Date day, String request_checkin, String request_checkout, String reason, String feedback, String mail, String name) {
        this.id = id;
        this.day = day;
        this.request_checkin = request_checkin;
        this.request_checkout = request_checkout;
        this.reason = reason;
        this.feedback = feedback;
        this.mail = mail;
        this.name = name;
    }

    public Timesheet(int id, Date day, String request_checkin, String request_checkout, String reason, String feedback) {
        this.id = id;
        this.day = day;
        this.request_checkin = request_checkin;
        this.request_checkout = request_checkout;
        this.reason = reason;
        this.feedback = feedback;

    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRequest_checkin() {
        return request_checkin;
    }

    public void setRequest_checkin(String request_checkin) {
        this.request_checkin = request_checkin;
    }

    public String getRequest_checkout() {
        return request_checkout;
    }

    public void setRequest_checkout(String request_checkout) {
        this.request_checkout = request_checkout;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
