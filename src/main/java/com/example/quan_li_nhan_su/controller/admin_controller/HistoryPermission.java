package com.example.quan_li_nhan_su.controller.admin_controller;

import com.example.quan_li_nhan_su.dao.HistoryPermissionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoryPermission {

    HistoryPermissionDao historyPermissionDao = new HistoryPermissionDao();

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String historyPermission(@RequestParam(name = "search", required = false) String search,
                                    Model model){
        model.addAttribute("group", '6');
        model.addAttribute("mode", '4');
        model.addAttribute("search", search);
        if (search != null){
            model.addAttribute("listPermission", historyPermissionDao.listPermissionBySearch(search));

        }else {
            model.addAttribute("listPermission", historyPermissionDao.listPermission());
        }
        return "Edit";
    }
}
