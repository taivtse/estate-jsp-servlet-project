package com.laptrinhjavaweb.orm.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SessionFactory {
    private static final ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle("database");
    private static final String dbUrl = DATABASE_BUNDLE.getString("db.url");
    private static final String dbUsername = DATABASE_BUNDLE.getString("db.username");
    private static final String dbPassword = DATABASE_BUNDLE.getString("db.password");

    static {
        try {
            Class.forName(DATABASE_BUNDLE.getString("db.driver"));
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
