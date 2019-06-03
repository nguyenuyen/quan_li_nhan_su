package com.example.quan_li_nhan_su.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class common {

    public String getCurrentDate(String s) throws ParseException {
        String[] str = s.split(" ");
        return str[0];
    }

    public String formatDate(String date) {
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

    public String getFirstDateOfMonth() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String formatted = formatter.format(cal.getTime());
        return formatted;
    }

    public float getTimeRequest(String startDateRequite, String endDateRequite) {
        // set the new date format
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

        //Date currentDate = new Date();
        //PLus 24h
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 24);

        Date date1 = null;
        Date date2 = null;
        float total = 0;
        float getDaysDiff = 0;
        float getDiff = 0;

        try {
            String[] start_date = this.splitString(startDateRequite);
            String[] end_date = this.splitString(endDateRequite);

            LocalTime start = LocalTime.parse(start_date[1]);
            LocalTime stop = LocalTime.parse(end_date[1]);

            LocalTime target1 = LocalTime.parse("08:30:00");
            LocalTime target2 = LocalTime.parse("12:00:00");
            LocalTime target3 = LocalTime.parse("13:00:00");

            LocalTime target4 = LocalTime.parse("17:30:00");

            if (start.isBefore(target1)) {
                start = target1;
            }
            if (start.isAfter(target4)) {
                start = LocalTime.parse("00:00:00");
            }

            if (start.isBefore(target3) && start.isAfter(target2)) {
                start = target3;
            }


            if (stop.isAfter(target2) && stop.isBefore(target3)) {
                stop = target2;
            }

            if (stop.isBefore(target1)) {
                stop = target1;
            }

            if (stop.isAfter(target4)) {
                stop = target4;
            }

            String startDateAfter = start_date[0];
            String endDateAfter = end_date[0];
            float getDiff1 = 0;
            float getDaysDiff1 = 0;

            date1 = simpleDateFormat1.parse(startDateAfter);
            date2 = simpleDateFormat1.parse(endDateAfter);

            getDiff = date2.getTime() - date1.getTime();
            getDaysDiff = getDiff / (24 * 60 * 60 * 1000);

            if ((start.isBefore(target2) || start.equals(target1)) && (stop.isBefore(target4) || stop.equals(target4))) {
                getDiff1 = stop.toSecondOfDay() - start.toSecondOfDay() - 3600;
            } else {
                getDiff1 = stop.toSecondOfDay() - start.toSecondOfDay();
            }

            getDaysDiff1 = getDiff1 / (8 * 60 * 60);

            total = getDaysDiff + getDaysDiff1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public float getTimeOt(String startDateRequite, String endDateRequite) {
        // set the new date format
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Date date1 = null;
        Date date2 = null;
        float getDaysDiff = 0;
        float getDiff = 0;

        try {
            date1 = simpleDateFormat.parse(startDateRequite + ":00");
            date2 = simpleDateFormat.parse(endDateRequite + ":00");

            getDiff = date2.getTime() - date1.getTime();
            getDaysDiff = getDiff / (60 * 60 * 1000); ;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getDaysDiff;
    }


    public String[] splitString(String s) {
        String[] str = s.split(" ");
        return str;
    }
}
