package com.example.quan_li_nhan_su.dao;

import com.example.quan_li_nhan_su.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public int login(String mail, String pass) {
        Connection connection = null;

        String query = "SELECT  type FROM staff WHERE mail = ? AND password = ? ";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, mail);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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
