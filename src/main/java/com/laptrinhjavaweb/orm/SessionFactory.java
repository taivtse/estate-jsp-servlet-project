package com.laptrinhjavaweb.orm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SessionFactory {
    private static final ResourceBundle DATABASE = ResourceBundle.getBundle("database");

    public static Connection openSession() {
        try {
            Class.forName(DATABASE.getString("db.driver"));
            String url = DATABASE.getString("db.url");
            String user = DATABASE.getString("db.username");
            String password = DATABASE.getString("db.password");
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
