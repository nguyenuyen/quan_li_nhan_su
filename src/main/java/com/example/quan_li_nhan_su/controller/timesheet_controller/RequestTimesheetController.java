package com.example.quan_li_nhan_su.controller.timesheet_controller;

import com.example.quan_li_nhan_su.dao.TimesheetDao;
import com.example.quan_li_nhan_su.dao.VacationDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
public class RequestTimesheetController {

    TimesheetDao timesheetDao = new TimesheetDao();
    VacationDao vacationDao = new VacationDao();

    @RequestMapping(value = "/timesheet/forget", method = RequestMethod.GET)
    public String Timesheet(@RequestParam(name = "date_check", required = false) String date_check,
                            HttpServletRequest request,
                            Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("group", '4');
        model.addAttribute("mode", '5');
        model.addAttribute("type", session.getAttribute("type"));
        model.addAttribute("mail", session.getAttribute("mail"));
        model.addAttribute("id", vacationDao.getUserID(session.getAttribute("mail").toString()));

        model.addAttribute("requestTimesheet", timesheetDao.getRequiteTimesheet(Date.valueOf(date_check)));

        model.addAttribute("listApprover", vacationDao.getApprover(session.getAttribute("mail").toString()));

        return "Edit";
    }
    @RequestMapping(value = "/timesheet/my/request", method = RequestMethod.POST)
    public String requiteTimesheet(@RequestParam(name = "date_check", required = false) String date_check,
                            @RequestParam(name = "checkin", required = false) String check_in,
                            @RequestParam(name = "checkout", required = false) String check_out,
                            @RequestParam(name = "approver", required = false) String approver,
                            @RequestParam(name = "reason", required = false) String reason,
                            HttpServletRequest request,

                            Model model) {
        model.addAttribute("group", '4');
        model.addAttribute("mode", '2');


        String startDate = date_check;
        String endDate =  date_check;

        HttpSession session = request.getSession();

//        timesheetDao.updateRequestTimesheet(date_check, check_in,check_out);
        vacationDao.insertRequestVacation(startDate, endDate, (float) 0, reason, Integer.parseInt(approver), session.getAttribute("mail").toString(), 2, check_in, check_out);

        model.addAttribute("requestTimesheet", timesheetDao.getHistoryTimesheet(Date.valueOf(date_check)));

        model.addAttribute("listApprover", vacationDao.getApprover(session.getAttribute("mail").toString()));

        LocalDate localDate = LocalDate.now();
        String start_date = localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
        String end_date = localDate.toString();

        model.addAttribute("startDate", start_date);
        model.addAttribute("endDate", localDate);
        model.addAttribute("timesheet", timesheetDao.getListMyRequestTimesheet(vacationDao.getUserID(session.getAttribute("mail").toString()),  start_date, localDate.toString()));

        return "Edit";
    }
}
