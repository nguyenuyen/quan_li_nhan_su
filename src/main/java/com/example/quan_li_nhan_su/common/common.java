package com.example.quan_li_nhan_su.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class common {

    public String getCurrentDate(String s) throws ParseException {
        String[] str = s.split(" ");
        return str[0];
    }
}
