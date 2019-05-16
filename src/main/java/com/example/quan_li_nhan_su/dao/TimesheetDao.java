package com.example.quan_li_nhan_su.dao;

import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.common.common;
import com.example.quan_li_nhan_su.model.Request;
import com.example.quan_li_nhan_su.model.Timesheet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimesheetDao extends common {

    public List<Timesheet> getListTimesheet(int id_user, String startDate, String endDate) {
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        List listTimesheet = new ArrayList();

        query.append("SELECT * FROM timesheet WHERE id_user = ? AND day BETWEEN ? AND ? ORDER BY day DESC");

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setInt(1, id_user);

                ps.setDate(2, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDate));



            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTimesheet.add(new Timesheet(rs.getInt("id"), rs.getDate("day"), rs.getTime("checkin").toString(), rs.getTime("checkout").toString(), rs.getInt("history")));
            }
            return listTimesheet;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Timesheet getRequiteTimesheet(Date date) {
        Connection connection = null;

        String query = "  SELECT  * FROM timesheet WHERE day = ?";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Timesheet(rs.getInt("id"), rs.getDate("day"), rs.getTime("checkin").toString(), rs.getTime("checkout").toString(), rs.getInt("history"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Timesheet> getHistoryTimesheet(Date date) {
        Connection connection = null;
        List<Timesheet> timesheetList = new ArrayList<>();
        String query = "SELECT  * FROM timesheet WHERE day = ? AND history = 1";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 timesheetList.add(new Timesheet(rs.getInt("id"), rs.getDate("day"), rs.getTime("checkin").toString(), rs.getTime("checkout").toString(), rs.getInt("history")));
            }
            return timesheetList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int updateTimesheet(String date, String checkin, String checkout) {
        Connection connection = null;

        String query = "UPDATE timesheet SET checkin= ?, checkout= ?, history=1 WHERE day = ?";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, checkin);
            ps.setString(2, checkout);
            ps.setString(3, date);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


}
