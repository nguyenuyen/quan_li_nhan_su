package com.example.quan_li_nhan_su.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/home&group=1&mode=1", method = RequestMethod.GET)
    public String Home(Model model){
        model.addAttribute("group", '1');
        model.addAttribute("mode", '1');

        return "Edit";
    }
}
