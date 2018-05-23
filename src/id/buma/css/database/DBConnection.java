/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.spt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class DBConnection {
    
    private Connection conn;
    
    public Connection getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s1 = "jdbc:mysql://localhost:3306/sim_tr?user=root&password=";
            String s2 = "jdbc:mysql://192.168.208.98:3306/sim_tr?user=admintr&password=ptpn7@jaya&useSSL=false";
            String s3 = "jdbc:mysql://192.168.39.150:3306/simpg?user=root&password=tiptpn7&useSSL=false";
            conn = DriverManager.getConnection(s3);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
