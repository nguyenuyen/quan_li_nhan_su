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

        query.append("SELECT * FROM timesheet WHERE id_user = ? AND date_check BETWEEN ? AND ? ORDER BY date_check DESC");

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setInt(1, id_user);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTimesheet.add(new Timesheet(rs.getInt("id"), rs.getDate("date_check"), rs.getTime("checkin").toString(), rs.getTime("checkout").toString(), rs.getInt("history")));
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

        String query = "  SELECT  * FROM timesheet WHERE date_check = ?";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Timesheet(rs.getInt("id"), rs.getDate("date_check"), rs.getTime("checkin").toString(), rs.getTime("checkout").toString(), rs.getInt("history"));
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
        String query = "SELECT  * FROM timesheet WHERE date_check = ? AND history = 1";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 timesheetList.add(new Timesheet(rs.getInt("id"), rs.getDate("date_check"), rs.getTime("checkin").toString(), rs.getTime("checkout").toString(), rs.getInt("history")));
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

    public int updateTimesheet(String date_check, String checkin, String checkout) {
        Connection connection = null;

        String query = "UPDATE timesheet SET checkin = ?::time , checkout = ?::time  WHERE   date_check = ?::date ";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, checkin);
            ps.setString(2, checkout);
            ps.setString(3, date_check);
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

    public int updateRequestTimesheet(String date_check, String checkin, String checkout) {
        Connection connection = null;

        String query = "UPDATE timesheet SET request_checkin = ?::time , request_checkout = ?::time  WHERE   date_check = ?::date ";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, checkin);
            ps.setString(2, checkout);
            ps.setString(3, date_check);
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


    public List<Timesheet> getListMyRequestTimesheet(int id_user, String startDate, String endDate) {
        Connection connection = null;

        List listTimesheet = new ArrayList();

        String query = ("SELECT r.id, t.date_check, r.request_checkin, r.request_checkout, r.reason, r.feedback, r.id_approver FROM timesheet as t, request r\n" +
                "WHERE t.id_user = ? AND r.type = 2 AND t.history = 1 AND cast (date_check as varchar ) like start_day AND date_check BETWEEN ? AND ? ORDER BY date_check DESC");

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id_user);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTimesheet.add(new Timesheet( rs.getInt("id"), rs.getDate("date_check"), rs.getString("request_checkin"), rs.getString("request_checkout"), rs.getString("reason"), rs.getString("feedback")));
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

    public int updateRequestTimesheet(String id) {
        Connection connection = null;

        String query = "UPDATE request SET feedback = 0 WHERE id = ?";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));

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

    public List<Request> getListRequest(int type, int id_team, int id_department, int type_request, int id, String start_date, String end_date){
        Connection connection = null;
        VacationDao vacationDao = new VacationDao();
        try {
            List listTimesheet = new ArrayList();
            String query = "SELECT r.id, t.date_check, r.request_checkin, r.request_checkout, r.reason, r.feedback, r.id_approver, s.mail, s.name FROM timesheet as t, request r, staff s WHERE s.type < ? AND s.id_team = ? AND s.id_department = ? AND s.id = r.id_user AND r.type = ?\n" +
                    "AND id_approver = ? AND r.feedback ISNULL AND r.start_day BETWEEN ? AND ? AND t.history = 1 AND cast (date_check as varchar ) like start_day ORDER BY feedback DESC";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, type);
            ps.setInt(2, id_team);
            ps.setInt(3, id_department);
            ps.setInt(4, type_request);
            ps.setInt(5, id);
            ps.setDate(6, Date.valueOf(start_date));
            ps.setDate(7, Date.valueOf(end_date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listTimesheet.add(new Timesheet( rs.getInt("id"), rs.getDate("date_check"), rs.getString("request_checkin"), rs.getString("request_checkout"), rs.getString("reason"), rs.getString("feedback"), rs.getString("mail"), rs.getString("name")));
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
