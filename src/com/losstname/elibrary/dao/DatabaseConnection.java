package com.losstname.elibrary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by losstname on 6/5/17.
 */
public class DatabaseConnection {
    public static Connection getConn(){
        Connection conn = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/elibrary",
                USER = "root",
                PASS = "root";
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
