package com.example.quan_li_nhan_su.controller.timesheet_controller;

import com.example.quan_li_nhan_su.dao.TimesheetDao;
import com.example.quan_li_nhan_su.dao.VacationDao;
import com.example.quan_li_nhan_su.dao.VacationStaffDao;
import com.example.quan_li_nhan_su.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
public class StaffTimesheetController {

    VacationStaffDao vacationStaffDao = new VacationStaffDao();
    TimesheetDao timesheetDao = new TimesheetDao();
    VacationDao vacationDao = new VacationDao();

    @RequestMapping(value = "/timesheet/staff", method = RequestMethod.GET)
    public String Timesheet(@RequestParam(name = "startDate", required = false) String startDate,
                            @RequestParam(name = "endDate", required = false) String endDate,
                            @RequestParam(name = "id", required = false) String id,
                            @RequestParam(name = "requite", required = false) String requite,
                            @RequestParam(name = "checkin", required = false) String checkin,
                            @RequestParam(name = "checkout", required = false) String checkout,
                            @RequestParam(name = "date_check", required = false) String date_check,

                            Model model) {
        LocalDate localDate = LocalDate.now();
        String start_date = localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
        String end_date = localDate.toString();

        model.addAttribute("group", '4');
        model.addAttribute("mode", '3');
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        if (id != null) {
            vacationDao.updateFeedback(Integer.parseInt(id), Integer.parseInt(requite));
        }

        if("1".equals(requite)){
            timesheetDao.updateTimesheet(date_check, checkin, checkout);
        }

        Staff staff = vacationStaffDao.getInfo("a6@gmail.com");

        if(startDate == null && endDate == null){
            model.addAttribute("timesheet", timesheetDao.getListRequest(staff.getType(), staff.getId_team(), staff.getid_department(), 2, staff.getId(), start_date, end_date));

        }else {
            model.addAttribute("timesheet", timesheetDao.getListRequest(staff.getType(), staff.getId_team(), staff.getid_department(), 2, staff.getId(), startDate, endDate));
        }

        return "Edit";
    }
}
