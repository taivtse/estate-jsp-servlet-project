package com.laptrinhjavaweb.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public static Connection getConnection() {
        try {
            Class.forName(resourceBundle.getString("db.driver"));
            String url = resourceBundle.getString("db.url");
            String user = resourceBundle.getString("db.username");
            String password = resourceBundle.getString("db.password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
