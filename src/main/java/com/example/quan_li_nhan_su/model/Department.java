package com.example.quan_li_nhan_su.model;

public class Department {
    int id;
    String name;
    String mail_admin;

    public Department(int id, String name, String mail_admin) {
        this.id = id;
        this.name = name;
        this.mail_admin = mail_admin;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail_admin() {
        return mail_admin;
    }

    public void setMail_admin(String mail_admin) {
        this.mail_admin = mail_admin;
    }
}
