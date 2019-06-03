package com.example.quan_li_nhan_su.controller.admin_controller;

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
public class Timesheet {
    TimesheetDao timesheetDao = new TimesheetDao();
    VacationDao vacationDao = new VacationDao();

    @RequestMapping(value = "/timesheet/admin", method = RequestMethod.GET)
    public String Timesheet(@RequestParam(name = "startDate", required = false) String startDate,
                            @RequestParam(name = "endDate", required = false) String endDate,
                            @RequestParam(name = "search_employee", required = false) String searchEmployee,
                            HttpServletRequest request,
                            Model model) {
        LocalDate localDate = LocalDate.now();
        String start_date = localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
        String end_date = localDate.toString();
        HttpSession session = request.getSession();

        model.addAttribute("group", '6');
        model.addAttribute("mode", '3');
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        if(searchEmployee != null){
            model.addAttribute("search_employee", searchEmployee);
        }else {
            model.addAttribute("search_employee", "");
        }
        if (startDate == null && endDate == null) {
            model.addAttribute("timesheet", timesheetDao.getListTimesheetAdmin(searchEmployee, start_date, end_date));
        } else {
            model.addAttribute("timesheet", timesheetDao.getListTimesheetAdmin(searchEmployee, startDate, endDate));
        }

        return "Edit";
    }

}
