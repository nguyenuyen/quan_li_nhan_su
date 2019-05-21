package com.example.quan_li_nhan_su.controller.admin_controller;

import com.example.quan_li_nhan_su.dao.AdminDao;
import com.example.quan_li_nhan_su.dao.VacationDao;
import com.example.quan_li_nhan_su.dao.VacationStaffDao;
import com.example.quan_li_nhan_su.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class RequestVacationController {

    VacationStaffDao vacationStaffDao = new VacationStaffDao();
    VacationDao vacationDao = new VacationDao();
    AdminDao adminDao = new AdminDao();

    @RequestMapping(value = "/request/staff/vacation", method = RequestMethod.GET)
    public String RequestVacation(@RequestParam(name = "id", required = false) String id,
                                  @RequestParam(name = "requite", required = false) String requite,
                                  @RequestParam(name = "search", required = false) String search,
                                  Model model) {
        model.addAttribute("group", '6');
        model.addAttribute("mode", '1');
        model.addAttribute("search", search);
        if (id != null) {
            vacationDao.updateFeedback(Integer.parseInt(id), Integer.parseInt(requite));
        }

        if (search == null) {
            model.addAttribute("listRequite", adminDao.listRequiteVacationBySearchAdmin("", 0));
            model.addAttribute("count", adminDao.countRequiteVacationBySearchAdmin("", 0));
        } else {
            model.addAttribute("listRequite", adminDao.listRequiteVacationBySearchAdmin(search, 0));
            model.addAttribute("count", adminDao.countRequiteVacationBySearchAdmin(search, 0));
        }
        return "Edit";
    }
}
