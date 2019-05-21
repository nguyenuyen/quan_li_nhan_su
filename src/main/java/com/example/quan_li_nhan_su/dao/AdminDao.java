package com.example.quan_li_nhan_su.dao;

import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.model.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends VacationDao{
    public List<Request> listRequiteVacationBySearchAdmin(String search, int type){
        Connection connection = null;
        try {
            List listRequest = new ArrayList();
            String query = "SELECT r.*, s.mail, s.name FROM request r, staff s WHERE (r.start_day like '%" + search + "%' OR r.end_day like '%" + search + "%' OR r.reason like '%" + search + "%' OR r.id_approver = (SELECT id FROM staff WHERE mail like '%" + search + "%')) AND r.type= ? AND r.id_user = s.id AND r.feedback ISNULL ORDER BY feedback DESC";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listRequest.add(new Request(rs.getInt("id"),rs.getString("start_day"), rs.getString("end_day"), rs.getString("reason"), rs.getString("type"), getCurrentDate(rs.getString("day_request")), rs.getString("feedback"), rs.getFloat("number_date"), getMail(rs.getInt("id_approver")), rs.getString("mail"), rs.getString("name")));
            }
            return listRequest;

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

    public int countRequiteVacationBySearchAdmin(String search, int type){
        Connection connection = null;
        try {
            List listRequest = new ArrayList();
            String query = "SELECT  COUNT (r.id) FROM request r, staff s WHERE (r.start_day like '%" + search + "%' OR r.end_day like '%" + search + "%' OR r.reason like '%" + search + "%' OR r.id_approver = (SELECT id FROM staff WHERE mail like '%" + search + "%')) AND r.type= ? AND r.id_user = s.id  AND r.feedback ISNULL";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
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


}
