package com.example.quan_li_nhan_su.controller;

import com.example.quan_li_nhan_su.dao.HomeDao;
import com.example.quan_li_nhan_su.dao.VacationDao;
import com.example.quan_li_nhan_su.dao.VacationStaffDao;
import com.example.quan_li_nhan_su.model.Staff;
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
public class HomeController {

    VacationStaffDao vacationStaffDao = new VacationStaffDao();
    VacationDao vacationDao = new VacationDao();
    HomeDao homeDao = new HomeDao();
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Home(HttpServletRequest request,
                       @RequestParam(name = "requite", required = false) String requite,
                       @RequestParam(name = "id", required = false) String id,
                       Model model) {

        HttpSession session = request.getSession();

        LocalDate localDate = LocalDate.now();
        String start_date = localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
        int m = localDate.getMonth().getValue();

        model.addAttribute("startDate", start_date);
        model.addAttribute("endDate", localDate);

        model.addAttribute("group", '1');
        model.addAttribute("mode", '1');

        model.addAttribute("number_vacation", (float)m);


        if(id != null){
            vacationDao.updateFeedback(Integer.parseInt(id), Integer.parseInt(requite));
        }

        Staff staff = vacationStaffDao.getInfo(session.getAttribute("mail").toString());

        float permission =  (float)m - homeDao.getNumberUsed(staff.getId());

        model.addAttribute("permission", permission);

        model.addAttribute("listRequite", homeDao.getListRequite(staff.getType(), staff.getId_team(), staff.getid_department(), staff.getId()));

        model.addAttribute("listRequiteVacation", vacationDao.getListRequiteVacation(session.getAttribute("mail").toString(), 0));

        model.addAttribute("listTimesheetIrregular", homeDao.getListTimesheetIrregular(staff.getId(), start_date, localDate.toString()));

        return "Edit";
    }
}
