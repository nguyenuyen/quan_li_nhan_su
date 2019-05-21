package com.example.quan_li_nhan_su.controller.timesheet_controller;

import com.example.quan_li_nhan_su.dao.TimesheetDao;
import com.example.quan_li_nhan_su.dao.VacationDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
public class MyRequestTimesheetController {
    TimesheetDao timesheetDao = new TimesheetDao();
    VacationDao vacationDao = new VacationDao();

    @RequestMapping(value = "/timesheet/my/request", method = RequestMethod.GET)
    public String requiteTimesheet(@RequestParam(name = "startDate", required = false) String startDate,
                                   @RequestParam(name = "endDate", required = false) String endDate,
                                   @RequestParam(name = "id", required = false) String id,

                                   Model model) {
        model.addAttribute("group", '4');
        model.addAttribute("mode", '2');
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        LocalDate localDate = LocalDate.now();
        String start_date = localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
        String end_date = localDate.toString();

        if(id != null){
            timesheetDao.updateRequestTimesheet(id);
        }

        if(startDate == null && endDate == null){
            model.addAttribute("timesheet", timesheetDao.getListMyRequestTimesheet(vacationDao.getUserID("uyen@gmail.com"),  start_date, end_date));
        }else {
            model.addAttribute("timesheet", timesheetDao.getListMyRequestTimesheet(vacationDao.getUserID("uyen@gmail.com"), startDate, endDate));
        }
        return "Edit";
    }
}
