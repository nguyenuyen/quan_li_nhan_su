package com.example.quan_li_nhan_su.dao;

import com.example.quan_li_nhan_su.ConnectionDatabase;
import com.example.quan_li_nhan_su.model.Contract;
import com.example.quan_li_nhan_su.model.Department;
import com.example.quan_li_nhan_su.model.Staff;
import com.example.quan_li_nhan_su.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {


    public int insertStaff(String name, String maNV, String mail, int id_department, int id_contract, String password, int maVanTay, int type, int id_team, String birthday, String day_start, String day_main, String day_last, String gender) {
        Connection connection = null;
        try {
            String query = "INSERT INTO staff(name, code, mail, id_department, id_contract, password, p_id, type, id_team, birthday, day_start, day_main, day_end, gender ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, maNV);
            ps.setString(3, mail);
            ps.setInt(4, id_department);
            ps.setInt(5, id_contract);
            ps.setString(6, password);
            ps.setInt(7, maVanTay);
            ps.setInt(8, type);
            ps.setInt(9, id_team);
            ps.setString(10, birthday);
            ps.setString(11, day_start);

            if (day_main == "") {
                ps.setString(12, null);
            } else {
                ps.setString(12, day_main);
            }

            if (day_last == "") {
                ps.setString(13, null);
            } else {
                ps.setString(13, day_last);
            }

            ps.setInt(14, Integer.parseInt(gender));

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    public int insertContract(int type, String day_start, String day_official, String day_end, String mail) {
        Connection connection = null;
        try {
            String query = "INSERT INTO contract(name, type, day_start, day_official, day_end, mail ) VALUES (?, ?, ?, ?, ?, ?)";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);

            if (type == 0) {
                ps.setString(1, "thực tập");
            } else if (type == 1) {
                ps.setString(1, "thử việc");
            } else if (type == 2) {
                ps.setString(1, "chính thức");
            }
            ps.setInt(2, type);
            ps.setString(3, day_start);

            if (day_official == null) {
                ps.setString(4, null);
            } else {
                ps.setString(4, day_official);
            }

            if (day_end == null) {
                ps.setString(5, null);
            } else {
                ps.setString(5, day_end);
            }

            ps.setString(6, mail);

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Team> getListTeam() {
        Connection connection = null;
        List<Team> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM team";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Team(rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Department> getListDepartment() {
        Connection connection = null;

        List<Department> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM department ORDER By id ASC";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Contract> getListContract() {
        Connection connection = null;

        List<Contract> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM contract ORDER By id ASC";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Contract(
                        rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Staff> getListStaff() {
        Connection connection = null;

        List<Staff> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM staff ORDER By id ASC";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Staff(rs.getInt("id"), rs.getString("name"), rs.getString("code"), rs.getString("mail"),
                        rs.getInt("p_id"), rs.getString("day_start"), rs.getString("day_main"), rs.getString("day_end"),
                        rs.getInt("id_department"), rs.getInt("id_contract"), rs.getInt("gender"), rs.getInt("type")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public Staff getInfoStaff(String code) {
        Connection connection = null;

        try {
            String query = "SELECT * FROM staff WHERE code = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               return new Staff(rs.getInt("id"), rs.getString("name"), rs.getString("code"), rs.getString("mail"),
                        rs.getInt("p_id"), rs.getString("day_start"), rs.getString("day_main"), rs.getString("day_end"),
                        rs.getInt("id_department"), rs.getInt("id_contract"), rs.getInt("gender"), rs.getInt("type"), rs.getInt("id_team"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Contract> getListContract(int id) {
        Connection connection = null;

        List<Contract> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM contract WHERE id =  ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Contract(
                        rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Team> getListTeam(int id) {
        Connection connection = null;
        List<Team> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM team WHERE id = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Team(rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Department> getListDepartment(int id) {
        Connection connection = null;

        List<Department> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM department WHERE id = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public int updateStaff(String namme, int maVT, int id_contract, int id_department, int id_team, String day_start, String day_main, String day_end, String code) {
        Connection connection = null;

        try {
            String query = "UPDATE staff SET name = ?, p_id = ?, id_contract = ?, id_department = ?, id_team = ?, day_start = ?::date , day_main = ?::date , day_end = ?::date WHERE code = ?";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, namme);
            ps.setInt(2, maVT);
            ps.setInt(3, id_contract);
            ps.setInt(4, id_department);
            ps.setInt(5, id_team);
            ps.setString(6, day_start);

            if(day_main != ""){
                ps.setString(7, day_main);
            }else {
                ps.setString(7, null);
            }

            if(day_end != ""){
                ps.setString(8, day_end);
            }else {
                ps.setString(8, null);
            }

            ps.setString(9, code);

            int rs = ps.executeUpdate();

            if(rs > 0){
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Staff> getListStaffByFilter(String id_contract, String id_department, String gender) {
        Connection connection = null;

        List<Staff> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM staff WHERE cast(id_contract as varchar )like '%" + id_contract + "%' AND cast (id_department as varchar) like '%" + id_department + "%'  AND cast (gender as varchar) like '%" + gender + "%'";
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Staff(rs.getInt("id"), rs.getString("name"), rs.getString("code"), rs.getString("mail"),
                        rs.getInt("p_id"), rs.getString("day_start"), rs.getString("day_main"), rs.getString("day_end"),
                        rs.getInt("id_department"), rs.getInt("id_contract"), rs.getInt("gender"), rs.getInt("type")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public int countRecord(String id_contract, String id_department, String gender) {
        Connection connection = null;

        String query = "SELECT  COUNT (id) FROM staff WHERE cast(id_contract as varchar )like '%" + id_contract + "%' AND cast (id_department as varchar) like '%" + id_department + "%'  AND cast (gender as varchar) like '%" + gender + "%'";

        try {
            connection = ConnectionDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);

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
