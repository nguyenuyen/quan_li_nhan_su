package com.example.quan_li_nhan_su.controller.vacation_controller;


import com.example.quan_li_nhan_su.dao.VacationDao;
import com.example.quan_li_nhan_su.dao.VacationStaffDao;
import com.example.quan_li_nhan_su.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


@Controller
public class StaffVacationController {
    VacationDao vacationDao = new VacationDao();
    VacationStaffDao vacationStaffDao = new VacationStaffDao();
    public int i = 1;

    @RequestMapping(value = "/vacationStaff", method = RequestMethod.GET)
    public String RequiteStaffVacation(@RequestParam(name = "id", required = false) String id,
                                       @RequestParam(name = "requite", required = false) String requite,
                                       @RequestParam(name = "search", required = false) String search,
                                       HttpServletResponse response,
                                       HttpServletRequest request,
                                       Model model) throws IOException {
        model.addAttribute("group", '2');
        model.addAttribute("mode", '2');
        model.addAttribute("search", search);
        HttpSession session = request.getSession();

        if (id != null) {
            vacationDao.updateFeedback(Integer.parseInt(id), Integer.parseInt(requite));
        }
        Staff staff = vacationStaffDao.getInfo(session.getAttribute("mail").toString());
        if(search != null){
            model.addAttribute("listRequite", vacationStaffDao.getListRequiteVacationBySearch(search, staff.getType(), staff.getId_team(), staff.getid_department(), 0, staff.getId()));
        }else {
            model.addAttribute("listRequite", vacationStaffDao.getListRequiteVacationBySearch("", staff.getType(), staff.getId_team(), staff.getid_department(), 0, staff.getId()));

        }

        return "Edit";
    }

//    @RequestMapping(value = "/download", method = RequestMethod.POST)
//    public void downloadFile(@RequestParam(name = "search", required = false) String search,
//                             @RequestParam(name = "act", required = false) String act,
//                             HttpServletResponse response) throws IOException {
//
//
////            if (search != null && "download".equals(act)) {
////                writeExcel(vacationStaffDao.getListRequiteVacationBySearch(search, "uyen@gmail.com"), excelFilePath);
////            }
////            if (search == null && "download".equals(act)) {
////                writeExcel(vacationDao.getListRequiteVacation("uyen@gmail.com"), excelFilePath);
////            }
//        File file = ResourceUtils.getFile("classpath:excel/request2.xlsx");
//
//            ServletOutputStream stream = null;
//            BufferedInputStream buf = null;
//            try {
//                stream = response.getOutputStream();
//                // set response headers
//                response.setContentType("application/vnd.ms-excel");
//                response.setContentType("APPLICATION/OCTET-STREAM");
//                response.addHeader("Content-Disposition", "attachment; filename=excel.xlsx");
//                response.setContentLength((int) file.length());
//                buf = new BufferedInputStream(new FileInputStream(file));
//                int readBytes = 0;
//                while ((readBytes = buf.read()) != -1)
//                    stream.write(readBytes);
//            } finally {
//                if (stream != null)
//                    stream.flush();
//                stream.close();
//                if (buf != null)
//                    buf.close();
//            }
//
//    }
}
