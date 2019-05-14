package com.example.quan_li_nhan_su.model;


public class Staff {
    int id;
    String name;
    int phone;
    int code;
    String mail;
    int id_team;
    int id_department;
    int id_contract;
    String password;
    int p_id; // chung minh thu
    String feedback;
    int type; // phan quyen

    public Staff(int id, String name, int phone, int code, String mail, int id_team, int id_department, int id_contract, String password, int p_id, String feedback, int type) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.code = code;
        this.mail = mail;
        this.id_team = id_team;
        this.id_department = id_department;
        this.id_contract = id_contract;
        this.password = password;
        this.p_id = p_id;
        this.feedback = feedback;
        this.type = type;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public int getid_department() {
        return id_department;
    }

    public void setid_department(int id_department) {
        this.id_department = id_department;
    }

    public int getId_contract() {
        return id_contract;
    }

    public void setId_contract(int id_contract) {
        this.id_contract = id_contract;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}