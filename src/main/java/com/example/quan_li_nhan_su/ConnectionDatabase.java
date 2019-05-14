package com.example.quan_li_nhan_su;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    public static Connection getConnecttion() {
        Connection cons = null;
        try {
            Class.forName("org.postgresql.Driver");
            cons = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/QuanLiNhanSu?characterEncoding=UTF‑8", "postgres", "admin");
        } catch (ClassNotFoundException | SQLException e) {

        }
        return cons;
    }
}
