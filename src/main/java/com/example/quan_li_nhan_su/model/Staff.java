package com.example.quan_li_nhan_su.model;


public class Staff {
    int id;
    String name;
    String code;
    String mail;
    int id_team;
    int id_department;
    int id_contract;
    String password;
    int p_id; // chung minh thu
    int type; // phan quyen
    String birthday;
    String day_start;
    String day_main;
    String day_end;
    String name_team;
    String name_department;
    String name_contract;
    int gioi_tinh;

    public Staff(int id, String name, String code, String mail, int id_team, int id_department, int id_contract, String password, int p_id, int type) {
        this.id = id;
        this.name = name;

        this.code = code;
        this.mail = mail;
        this.id_team = id_team;
        this.id_department = id_department;
        this.id_contract = id_contract;
        this.password = password;
        this.p_id = p_id;
        this.type = type;
    }

    public Staff(int id, String name, String code, String mail, int p_id, String day_start, String day_main, String day_end, int id_department, int id_contract, int gioi_tinh, int type) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.mail = mail;
        this.p_id = p_id;
        this.day_start = day_start;
        this.day_main = day_main;
        this.day_end = day_end;
        this.id_department = id_department;
        this.id_contract = id_contract;
        this.gioi_tinh = gioi_tinh;
        this.type = type;
    }

    public Staff(int id, String name, String code, String mail, int p_id, String day_start, String day_main, String day_end, int id_department, int id_contract, int gioi_tinh, int type, int id_team) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.mail = mail;
        this.p_id = p_id;
        this.day_start = day_start;
        this.day_main = day_main;
        this.day_end = day_end;
        this.id_department = id_department;
        this.id_contract = id_contract;
        this.gioi_tinh = gioi_tinh;
        this.type = type;
        this.id_team = id_team;
    }

    public int getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(int gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public String getCode() {
        return code;
    }

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }

    public String getName_department() {
        return name_department;
    }

    public void setName_department(String name_department) {
        this.name_department = name_department;
    }

    public String getName_contract() {
        return name_contract;
    }

    public void setName_contract(String name_contract) {
        this.name_contract = name_contract;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDay_start() {
        return day_start;
    }

    public void setDay_start(String day_start) {
        this.day_start = day_start;
    }

    public String getDay_main() {
        return day_main;
    }

    public void setDay_main(String day_main) {
        this.day_main = day_main;
    }

    public String getDay_end() {
        return day_end;
    }

    public void setDay_end(String day_end) {
        this.day_end = day_end;
    }
}