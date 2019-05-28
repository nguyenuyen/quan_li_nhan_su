package com.example.quan_li_nhan_su.dao;


import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.model.Request;
import com.example.quan_li_nhan_su.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacationStaffDao extends VacationDao{
    public List<Request> getListRequiteVacationBySearch(String search, int type, int id_team, int id_department, int type_request, int id){
        Connection connection = null;
        try {
            List listRequest = new ArrayList();
            String query = "SELECT r.*, s.mail, s.name FROM request r, staff s WHERE (r.start_day like '%" + search + "%' OR r.end_day like '%" + search + "%' OR r.reason like '%" + search + "%' OR r.id_approver = (SELECT id FROM staff WHERE mail like '%" + search + "%')) AND s.type < ? AND s.id_team = ? AND s.id_department = ? AND s.id = r.id_user AND r.type = ? AND id_approver = ? AND r.feedback ISNULL ORDER BY feedback DESC";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, type);
            ps.setInt(2, id_team);
            ps.setInt(3, id_department);
            ps.setInt(4, type_request);
            ps.setInt(5, id);
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

    public Staff getInfo(String mail){
        Connection connection = null;
        try {
            String query = "SELECT * FROM staff WHERE id = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, getUserID(mail));
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Staff(rs.getInt("id"), rs.getString("name"), rs.getString("code"), rs.getString("mail"), rs.getInt("id_team"),
                        rs.getInt("id_department"), rs.getInt("id_contract"), rs.getString("password"), rs.getInt("p_id"), rs.getInt("type"));
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


}
