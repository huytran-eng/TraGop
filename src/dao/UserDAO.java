/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author admin
 */
public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    public boolean checkLogin(User user) {
        boolean result = false;
        String sql = "SELECT id,phoneNo,address,email,position FROM tblUser WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setPhoneNo(rs.getString("phoneNo"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setPosition(rs.getString("position"));
                result = true;
                System.out.println(user);
                System.out.println("-------------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
