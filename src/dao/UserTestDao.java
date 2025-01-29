/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import expensetracker.model.UserTestModel;

import java.sql.*;

/**
 *
 * @author Asus
 */
public class UserTestDao {
    MySqlConnection mysql = new MySqlConnection();
    
    public UserTestModel login(String email, String password){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM user_test where email = ? and password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery(); // read query
            if(result.next()){
                UserTestModel user  = new UserTestModel(
                    result.getString("email"),
                    result.getString("name"),
                    result.getString("password")
                );
                
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public void signup(String username, String password, String email) {
        Connection conn = mysql.openConnection(); // same
        
        String sql = "INSERT INTO user_test (name, password, email) VALUES (?, ?, ?)"; // database ko query raw
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
//            pstmt.setInt(4, 100);
            pstmt.executeUpdate(); // create, update, delete
        } catch (SQLException ex) {
            System.out.println("EX :" + ex);
        } finally {
            mysql.closeConnection(conn);
        }
    }
}
