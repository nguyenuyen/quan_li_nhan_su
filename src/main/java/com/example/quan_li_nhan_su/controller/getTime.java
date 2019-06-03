package com.example.quan_li_nhan_su.controller;

import com.example.quan_li_nhan_su.common.common;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class getTime {

    com.example.quan_li_nhan_su.common.common common = new common();

    @RequestMapping(value = "/getTime", method = RequestMethod.POST)
    public void getTimeRequite(@RequestParam(name = "startDate", required = false) String startDate,
                               @RequestParam(name = "endDate", required = false) String endtDate,
                               HttpServletResponse res) {

        BufferedOutputStream out = null;
        float result = 0;
        try {
            out = new BufferedOutputStream(res.getOutputStream());
            JSONObject jsonObject = new JSONObject();
            result = common.getTimeRequest(startDate, endtDate);

            jsonObject.put("result", result);

            res.setContentType("text/javascript+json; charset=utf-8");
            if (out != null) {
                out.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                    res.getOutputStream().flush();
                    res.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping(value = "/getTimeOt", method = RequestMethod.POST)
    public void getTimeRequiteOt(@RequestParam(name = "startDate", required = false) String startDate,
                               @RequestParam(name = "endDate", required = false) String endtDate,
                               HttpServletResponse res) {

        BufferedOutputStream out = null;
        float result = 0;
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            out = new BufferedOutputStream(res.getOutputStream());
            JSONObject jsonObject = new JSONObject();

            Date date1 = simpleDateFormat.parse(startDate + ":00");
            Date date2 = simpleDateFormat.parse(endtDate + ":00");

            long total = date2.getTime() - date1.getTime();

            if(total < 0){
                jsonObject.put("result", "FAIL");
            }else {
                result = common.getTimeOt(startDate, endtDate);

                jsonObject.put("result", result);
            }

            res.setContentType("text/javascript+json; charset=utf-8");
            if (out != null) {
                out.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                    res.getOutputStream().flush();
                    res.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
