package com.example.quan_li_nhan_su.controller.admin_controller;

import com.example.quan_li_nhan_su.dao.AdminDao;
import com.example.quan_li_nhan_su.dao.StaffDao;
import com.example.quan_li_nhan_su.dao.VacationDao;
import com.example.quan_li_nhan_su.dao.VacationStaffDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestStaffOt {
    VacationStaffDao vacationStaffDao = new VacationStaffDao();
    VacationDao vacationDao = new VacationDao();
    AdminDao adminDao = new AdminDao();
    StaffDao staffDao = new StaffDao();

    @RequestMapping(value = "/request/staff/ot", method = RequestMethod.GET)
    public String RequestVacation(@RequestParam(name = "id", required = false) String id,
                                  @RequestParam(name = "requite", required = false) String requite,
                                  @RequestParam(name = "search", required = false) String search,
                                  Model model){
        model.addAttribute("group", '6');
        model.addAttribute("mode", '2');
        model.addAttribute("search", search);
        model.addAttribute("department", staffDao.getListDepartment());

        if (id != null) {
            vacationDao.updateFeedback(Integer.parseInt(id), Integer.parseInt(requite));
        }

        if(search == null){
            model.addAttribute("listRequite", adminDao.listRequiteVacationBySearchAdmin("", 1));
            model.addAttribute("count", adminDao.countRequiteVacationBySearchAdmin("", 1));
        }else {
            model.addAttribute("listRequite", adminDao.listRequiteVacationBySearchAdmin(search, 1));
            model.addAttribute("count", adminDao.countRequiteVacationBySearchAdmin(search, 1));
        }
        return "Edit";
    }

    @RequestMapping(value = "/request/staff/filter", method = RequestMethod.POST)
    public String RequestVacation(@RequestParam(name = "division", required = false) String division,
                                  Model model){
        model.addAttribute("group", '6');
        model.addAttribute("mode", '2');
        model.addAttribute("phong_ban", division);
        model.addAttribute("department", staffDao.getListDepartment());




            model.addAttribute("listRequite", adminDao.listFilter(division, 1));
            model.addAttribute("count", adminDao.countFilter(division, 1));

        return "Edit";
    }
}
