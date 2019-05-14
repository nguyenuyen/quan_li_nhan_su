package com.example.quan_li_nhan_su.dao;

import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.common.common;
import com.example.quan_li_nhan_su.model.Request;
import com.example.quan_li_nhan_su.model.Timesheet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimesheetDao extends common {

    public List<Timesheet> getListRequiteVacation(int id_user, String startDate, String endDate){
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        List listTimesheet = new ArrayList();

        query.append("SELECT * FROM timesheet WHERE id_user = ? ");
        if((startDate != "" && startDate != null) && (endDate != "" && endDate != null)){
            query.append("AND day BETWEEN ? AND ? ");
        }
        query.append("ORDER BY day DESC");

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setInt(1, id_user);
            if((startDate != "" && startDate != null) && (endDate != "" && endDate != null)){
               ps.setDate(2, Date.valueOf(formatDate(startDate)));
               ps.setDate(3, Date.valueOf(formatDate(endDate)));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listTimesheet.add(new Timesheet(rs.getInt("id"), rs.getDate("day"), getTime(rs.getTimestamp("start_day").toString()), getTime(rs.getTimestamp("end_day").toString())));
            }
            return listTimesheet;

        }catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
