package com.example.quan_li_nhan_su.dao;

import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.common.common;
import com.example.quan_li_nhan_su.model.Request;
import com.example.quan_li_nhan_su.model.Timesheet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeDao  extends VacationDao{

    public List<Request> getListRequite(int type, int id_team, int id_department, int id){
        Connection connection = null;
        String query = "SELECT r.*, s.mail, s.name FROM request r, staff s WHERE  s.type < ? AND s.id = r.id_user  AND id_approver = ? AND r.feedback ISNULL ORDER BY feedback DESC";

        if(type == 4){
            query = "SELECT r.*, s.mail, s.name FROM request r, staff s WHERE  s.type <= ? AND s.id = r.id_user  AND id_approver = ? AND r.feedback ISNULL ORDER BY feedback DESC";
        }

        try {

            List listRequest = new ArrayList();
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, type);
//            ps.setInt(2, id_team);
            if(type ==4){
                ps.setInt(2, id);
            }else {
//                ps.setInt(2, id_department);
                ps.setInt(2, id);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listRequest.add(new Request(rs.getInt("id"),rs.getString("start_day"), rs.getString("end_day"), rs.getString("reason"), rs.getString("type"), getCurrentDate(rs.getString("day_request")), rs.getString("feedback"), rs.getFloat("number_date"), getMail(rs.getInt("id_approver")), rs.getString("mail"), rs.getString("name")));
            }
            if(listRequest.size() == 0){
                return null;
            }else {
                return listRequest;
            }

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

    public float getNumberUsed(int id){
        Connection connection = null;

        String query = "SELECT SUM(number_date) FROM request WHERE type = 0 AND id_user = ? AND feedback = 1";

        try {

            List listRequest = new ArrayList();
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getFloat(1);
            }

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
        return 0;
    }

    public List<Timesheet> getListTimesheetIrregular (int id_user, String startDate, String endDate) {
        common common = new common();
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        List listTimesheet = new ArrayList();

        query.append("SELECT id, date_check, checkin, checkout, history FROM timesheet WHERE id_user = ? AND (checkin > '08:45:00' OR checkout < '17:30:00') AND (date_check >= ? AND date_check <= ? )");

        String sql = "SELECT EXTRACT(DOW FROM DATE ?)";
        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.setInt(1, id_user);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int dayOfWeek = common.dayOfWeek(rs.getDate("date_check"));
                listTimesheet.add(new Timesheet(rs.getInt("id"), rs.getDate("date_check"), rs.getTime("checkin").toString(), rs.getTime("checkout").toString(), rs.getInt("history"), dayOfWeek));
            }

            if(listTimesheet.size() > 0){
                return listTimesheet;
            }else {
                return null;
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
}
