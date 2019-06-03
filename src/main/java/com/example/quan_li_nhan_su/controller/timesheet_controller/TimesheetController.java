package com.example.quan_li_nhan_su.controller.timesheet_controller;

import com.example.quan_li_nhan_su.common.common;
import com.example.quan_li_nhan_su.dao.TimesheetDao;
import com.example.quan_li_nhan_su.dao.VacationDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
class TimesheetController extends common {

    TimesheetDao timesheetDao = new TimesheetDao();
    VacationDao vacationDao = new VacationDao();

    @RequestMapping(value = "/timesheet", method = RequestMethod.GET)
    public String Timesheet(@RequestParam(name = "startDate", required = false) String startDate,
                            @RequestParam(name = "endDate", required = false) String endDate,
                            HttpServletRequest request,
                            Model model) {
        LocalDate localDate = LocalDate.now();
        String start_date = localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
        String end_date = localDate.toString();
        HttpSession session = request.getSession();

        model.addAttribute("group", '4');
        model.addAttribute("mode", '1');
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        if(startDate == null && endDate == null){
            model.addAttribute("timesheet", timesheetDao.getListTimesheet(vacationDao.getUserID(session.getAttribute("mail").toString()),  start_date, end_date));
        }else {
            model.addAttribute("timesheet", timesheetDao.getListTimesheet(vacationDao.getUserID(session.getAttribute("mail").toString()), startDate, endDate));
        }

        return "Edit";
    }
}
