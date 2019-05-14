package com.example.quan_li_nhan_su.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class common {

    public String getCurrentDate(String s) throws ParseException {
        String[] str = s.split(" ");
        return str[0];
    }

    public String getTime(String s) throws ParseException {
        String[] str = s.split("\\.");
        return str[0];
    }

    public String formatDate(String date){
        Date initDate = null;
        String parsedDate = null;
        try {
            initDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            parsedDate = formatter.format(initDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    public String getFirstDateOfMonth(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String formatted = formatter.format(cal.getTime());
        return formatted;
    }
}
