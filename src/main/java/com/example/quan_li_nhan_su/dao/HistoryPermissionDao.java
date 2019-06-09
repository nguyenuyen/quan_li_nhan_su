package com.example.quan_li_nhan_su.dao;

import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.model.Request;
import com.example.quan_li_nhan_su.model.Timesheet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryPermissionDao {
    public List listPermission(){
        List listPermission = new ArrayList();
        Connection connection = null;
        try {
            String query = "SELECT s.id, s.name, s.mail, r.number_date, r.day_request FROM staff s, request r WHERE  r.id_user = s.id AND  r.feedback = 1  AND r.type = 0";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listPermission.add(new Request(rs.getInt("id"), rs.getString("day_request"), rs.getFloat("number_date"), rs.getString("mail"), rs.getString("name")));
            }
            return listPermission;
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
        return listPermission;
    }

    public List listPermissionBySearch(String search){
        List listPermission = new ArrayList();
        Connection connection = null;
        try {
            String query = "SELECT s.id, s.name, s.mail, r.number_date, r.day_request FROM staff s, request r WHERE  r.id_user = s.id AND  r.feedback = 1  AND r.type = 0 AND (s.name like '%" + search+ "%' OR s.mail like '%" + search + "%')";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listPermission.add(new Request(rs.getInt("id"), rs.getString("day_request"), rs.getFloat("number_date"), rs.getString("mail"), rs.getString("name")));
            }
            return listPermission;
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
        return listPermission;
    }

}
