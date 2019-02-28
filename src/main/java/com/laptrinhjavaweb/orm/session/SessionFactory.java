package com.laptrinhjavaweb.orm.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SessionFactory {
    private static final ResourceBundle DATABASE = ResourceBundle.getBundle("database");
    private static final String dbUrl = DATABASE.getString("db.url");
    private static final String dbUsername = DATABASE.getString("db.username");
    private static final String dbPassword = DATABASE.getString("db.password");

    static {
        try {
            Class.forName(DATABASE.getString("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Session openSession() {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            connection.setAutoCommit(false);

            return new SessionImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
