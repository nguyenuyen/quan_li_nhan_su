package com.example.quan_li_nhan_su.controller.staff_controller;

import com.example.quan_li_nhan_su.dao.StaffDao;
import com.example.quan_li_nhan_su.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
public class AddStaffController {
    StaffDao staffDao = new StaffDao();

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String requiteTimesheet(@RequestParam(name = "startDate", required = false) String startDate,
                                   @RequestParam(name = "endDate", required = false) String endDate,
                                   @RequestParam(name = "id", required = false) String id,
                                   Model model) {

        model.addAttribute("group", '5');
        model.addAttribute("mode", '2');
        model.addAttribute("team", staffDao.getListTeam());
        model.addAttribute("department", staffDao.getListDepartment());
        model.addAttribute("contract", staffDao.getListContract());

        return "Edit";
    }

    @RequestMapping(value = "/staff/add", method = RequestMethod.POST)
    public String requiteTimesheet(@RequestParam(name = "first_name", required = false) String first_name,
                                   @RequestParam(name = "middle_name", required = false) String middle_name,
                                   @RequestParam(name = "last_name", required = false) String last_name,
                                   @RequestParam(name = "mail", required = false) String mail,
                                   @RequestParam(name = "code_fingerprint", required = false) String code_fingerprint,
                                   @RequestParam(name = "code", required = false) String code,
                                   @RequestParam(name = "contract", required = false) String contract,
                                   @RequestParam(name = "division", required = false) String division,
                                   @RequestParam(name = "team", required = false) String team,
                                   @RequestParam(name = "birth_day", required = false) String birth_day,
                                   @RequestParam(name = "gender", required = false) String gender,
                                   @RequestParam(name = "role", required = false) String role,
                                   @RequestParam(name = "start_working", required = false) String start_working,
                                   @RequestParam(name = "start_working_main", required = false) String start_working_main,
                                   @RequestParam(name = "start_working_end", required = false) String start_working_end,

                                   Model model) {

        model.addAttribute("group", '5');
        model.addAttribute("mode", '1');
        model.addAttribute("team", staffDao.getListTeam());
        model.addAttribute("department", staffDao.getListDepartment());
        model.addAttribute("contract", staffDao.getListContract());
        String name = first_name +" "+ middle_name +" "+ last_name;

        staffDao.insertStaff(name,code,mail,Integer.parseInt(division),Integer.parseInt(contract), mail,Integer.parseInt(code_fingerprint),
                Integer.parseInt(role),Integer.parseInt(team), birth_day, start_working, start_working_main, start_working_end, gender);

        model.addAttribute("count", staffDao.countRecord("", "", ""));

        model.addAttribute("listStaff", staffDao.getListStaff());


        return "Edit";
    }

    @RequestMapping(value = "/staff/add", method = RequestMethod.GET)
    public String requiteTimesheet(Model model) {

        model.addAttribute("group", '5');
        model.addAttribute("mode", '1');
        model.addAttribute("team", staffDao.getListTeam());
        model.addAttribute("department", staffDao.getListDepartment());
        model.addAttribute("contract", staffDao.getListContract());
        model.addAttribute("count", staffDao.countRecord("", "", ""));

        model.addAttribute("listStaff", staffDao.getListStaff());
        return "Edit";
    }

    @RequestMapping(value = "/staff/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(name = "code", required = false) String code,
                              Model model) {

        model.addAttribute("group", '5');
        model.addAttribute("mode", '3');
        model.addAttribute("team", staffDao.getListTeam());
        model.addAttribute("department", staffDao.getListDepartment());
        model.addAttribute("contract", staffDao.getListContract());

        Staff staff = staffDao.getInfoStaff(code);

        String[] name = staff.getName().split(" ");

        model.addAttribute("ho", name[0]);

        if(name.length ==3){
            model.addAttribute("dem", name[1]);
            model.addAttribute("ten", name[2]);
        }

        model.addAttribute("mail", staff.getMail());
        model.addAttribute("maVT", staff.getP_id());
        model.addAttribute("maNV", staff.getCode());
        model.addAttribute("ngay_sinh", staff.getBirthday());
        model.addAttribute("gioi_tinh", staff.getGioi_tinh());
        model.addAttribute("hop_dong", staff.getId_contract());
        model.addAttribute("phong_ban", staff.getId_department());
        model.addAttribute("team_", staff.getId_team());
        model.addAttribute("start", staff.getDay_start());
        model.addAttribute("main", staff.getDay_main());
        model.addAttribute("end", staff.getDay_end());

        model.addAttribute("code", code);
        return "Edit";
    }

    @RequestMapping(value = "/staff/edit", method = RequestMethod.POST)
    public String requiteEdit(@RequestParam(name = "first_name", required = false) String first_name,
                              @RequestParam(name = "middle_name", required = false) String middle_name,
                              @RequestParam(name = "last_name", required = false) String last_name,
                              @RequestParam(name = "mail", required = false) String mail,
                              @RequestParam(name = "code_fingerprint", required = false) String code_fingerprint,
                              @RequestParam(name = "code", required = false) String code,
                              @RequestParam(name = "contract", required = false) String contract,
                              @RequestParam(name = "division", required = false) String division,
                              @RequestParam(name = "team", required = false) String team,
                              @RequestParam(name = "start_working", required = false) String start_working,
                              @RequestParam(name = "start_working_main", required = false) String start_working_main,
                              @RequestParam(name = "start_working_end", required = false) String start_working_end,
                              Model model) {

        model.addAttribute("group", '5');
        model.addAttribute("mode", '1');
        model.addAttribute("team", staffDao.getListTeam());
        model.addAttribute("department", staffDao.getListDepartment());
        model.addAttribute("contract", staffDao.getListContract());
        model.addAttribute("count", staffDao.countRecord("", "", ""));

        String name = first_name +" "+ middle_name +" "+ last_name;

        staffDao.updateStaff(name, Integer.parseInt(code_fingerprint), Integer.parseInt(contract), Integer.parseInt(division), Integer.parseInt(team), start_working,start_working_main,start_working_end, code);

        model.addAttribute("listStaff", staffDao.getListStaff());

        return "Edit";
    }

    @RequestMapping(value = "/staff/filter", method = RequestMethod.POST)
    public String filterRequest(@RequestParam(name = "division", required = false) String division,
                                @RequestParam(name = "contract", required = false) String contract,
                                @RequestParam(name = "gender", required = false) String gender,
                                Model model) {

        model.addAttribute("group", '5');
        model.addAttribute("mode", '1');
        model.addAttribute("hop_dong", contract);
        model.addAttribute("phong_ban", division);
        model.addAttribute("gioi_tinh", gender);
        model.addAttribute("team", staffDao.getListTeam());
        model.addAttribute("department", staffDao.getListDepartment());
        model.addAttribute("contract", staffDao.getListContract());
        model.addAttribute("count", staffDao.countRecord(division, contract, gender));
        model.addAttribute("listStaff", staffDao.getListStaffByFilter(division, contract, gender));
        return "Edit";
    }
}
