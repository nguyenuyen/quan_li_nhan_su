package com.example.quan_li_nhan_su.dao;



import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.common.common;
import com.example.quan_li_nhan_su.model.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VacationDao extends common {

    /**
     * request myvacation
     * @param startDate
     * @param endDate
     * @param reason
     */
    public int insertRequestVacation(String startDate, String endDate, Float number_date, String reason, int approver, String mail, int type){
        Connection connection = null;
        try {
            String query = "INSERT INTO request(start_day, end_day, number_date, reason, type, id_approver, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setFloat(3, number_date);
            ps.setString(4, reason);
            ps.setInt(5, type);//0 : nghỉ phép
            ps.setInt(6, approver);
            ps.setInt(7, getUserID(mail));
            int rs = ps.executeUpdate();
            if(rs > 0){
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    public Map getApprover(String mail){
        Connection connection = null;
        try {
            Map listApprover = new HashMap();
            String query = "SELECT id,mail FROM staff where type = ?  ";// type la role cua nhan vien
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, getType(mail) + 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listApprover.put(rs.getInt("id"), rs.getString("mail"));
            }
            return listApprover;
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

    public int getUserID(String mail){
        Connection connection = null;
        int id = -1;
        try {
            String query = "SELECT id FROM staff where mail = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public int getType(String mail){
        Connection connection = null;
        int id = -1;
        try {
            String query = "SELECT type FROM staff where mail = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public List<Request> getListRequiteVacation(String mail, int type){
        Connection connection = null;
        try {
            List listRequest = new ArrayList();
            String query = "SELECT * FROM request WHERE id_user = ? AND type = ? ORDER BY feedback DESC";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, getUserID(mail));
            ps.setInt(2, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listRequest.add(new Request(rs.getInt("id"),rs.getString("start_day"), rs.getString("end_day"), rs.getString("reason"), rs.getString("type"), getCurrentDate(rs.getString("day_request")), rs.getString("feedback"), rs.getFloat("number_date"), getMail(rs.getInt("id_approver"))));
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

    public String getMail(int id){
        Connection connection = null;
        String mail = null;
        try {
            String query = "SELECT mail FROM staff WHERE id = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                mail = rs.getString("mail");
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
        return mail;
    }

    public boolean updateFeedback(int id, int requite){
        Connection connection = null;
        try {
            String query = "UPDATE request SET feedback = ? WHERE id = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, requite);
            ps.setInt(2, id);
            int rs = ps.executeUpdate();
            if(rs > 0) {
                return true;
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
        return false;
    }
}
