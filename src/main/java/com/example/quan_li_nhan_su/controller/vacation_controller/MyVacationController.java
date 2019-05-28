package com.example.quan_li_nhan_su.controller.vacation_controller;

import com.example.quan_li_nhan_su.dao.VacationDao;
import com.sun.deploy.net.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class MyVacationController {
    VacationDao vacationDao = new VacationDao();

    @RequestMapping(value = "/vacation", method = RequestMethod.GET)
    public String RequiteVacation(@RequestParam(name = "startDate", required = false) String startDate,
                                  @RequestParam(name = "endDate", required = false) String endtDate,
                                  @RequestParam(name = "workingDate", required = false) Float workingDate,
                                  @RequestParam(name = "approver", required = false) String approver,
                                  @RequestParam(name = "reason", required = false) String reason,
                                  @RequestParam(name = "act", required = false) String act,
                                  @RequestParam(name = "id", required = false) String id,
                                  HttpServletResponse res,
                                  Model model) {

        BufferedOutputStream out = null;

        model.addAttribute("group", '2');
        model.addAttribute("mode", '1');
        model.addAttribute("listApprover", vacationDao.getApprover("uyen@gmail.com"));

        if ("vacation".equals(act)) {
            try {
                out = new BufferedOutputStream(res.getOutputStream());
                JSONObject jsonObject = new JSONObject();
                int result = vacationDao.insertRequestVacation(startDate, endtDate, workingDate, reason, Integer.parseInt(approver), "uyen@gmail.com", 0,null, null);
                if (result == 1) {
                    jsonObject.put("result", "OK");
                } else {
                    jsonObject.put("result", "FAIL");
                }
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

        if(id != null){
            vacationDao.updateFeedback(Integer.parseInt(id), 0);
        }

        model.addAttribute("listRequite", vacationDao.getListRequiteVacation("uyen@gmail.com", 0));

        return "Edit";
    }


}
