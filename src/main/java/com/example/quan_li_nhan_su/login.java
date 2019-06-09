package com.example.quan_li_nhan_su;

import com.example.quan_li_nhan_su.dao.LoginDao;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class login {

    LoginDao loginDao = new LoginDao();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginform", method = RequestMethod.POST)
    public String login(@RequestParam(name = "mail", required = false) String mail,
                        @RequestParam(name = "password", required = false) String pass,
                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (mail != null && pass != null) {
            session.setAttribute("mail", mail);
            session.setAttribute("type", loginDao.login(mail, pass));
            if (loginDao.login(mail, pass) > 0) {
                return "redirect:/home";
            }
        } else {
            return "redirect:/login";
        }

        return "login";
    }
}
