package com.example.quan_li_nhan_su.controller.overtime_controller;


import com.example.quan_li_nhan_su.dao.VacationDao;
import com.example.quan_li_nhan_su.dao.VacationStaffDao;
import com.example.quan_li_nhan_su.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;

@Controller
public class StaffOvertimeController {
    VacationStaffDao vacationStaffDao = new VacationStaffDao();
    VacationDao vacationDao = new VacationDao();

    @RequestMapping(value = "/overtimeStaff", method = RequestMethod.GET)
    public String RequiteOvertimeStaff(@RequestParam(name = "id", required = false) String id,
                                       @RequestParam(name = "requite", required = false) String requite,
                                       @RequestParam(name = "search", required = false) String search,
                                       Model model) throws IOException {
        model.addAttribute("group", '3');
        model.addAttribute("mode", '2');
        model.addAttribute("search", search);
        if (id != null) {
            vacationDao.updateFeedback(Integer.parseInt(id), Integer.parseInt(requite));
        }
        Staff staff = vacationStaffDao.getInfo("a6@gmail.com");
        if(search != null){
            model.addAttribute("listRequite", vacationStaffDao.getListRequiteVacationBySearch(search, staff.getType(), staff.getId_team(), staff.getid_department(), 1, staff.getId()));
        }else {
            model.addAttribute("listRequite", vacationStaffDao.getListRequiteVacationBySearch("", staff.getType(), staff.getId_team(), staff.getid_department(), 1, staff.getId()));

        }
        return "Edit";
    }
}
