/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.sql.*;

/**
 *
 * @author Hammad
 */
public class Database {

    static final String DB_URL = "jdbc:mysql://localhost:3306/sda";
    static final String USER = "root";
    static final String PASS = "sheeda";

    public static Connection con() {
        Connection con = null;
        try {
            //register jbdc driver,not reeq for newer versions of jdk
            //Class.forName("com.mysql.jbdc.Driver");
            ///open a connection
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("connection successful");
            return con;

        } catch (Exception ex) {
            System.out.println("There was an error while connecting with DB");
            return null;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Connection c = con();
    }

}
