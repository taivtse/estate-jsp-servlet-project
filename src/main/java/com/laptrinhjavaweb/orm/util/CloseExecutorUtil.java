package com.laptrinhjavaweb.orm.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseExecutorUtil {
    public static void closeAllAfterExecute(Statement statement) {
        try {
            if (statement != null) {
                Connection connection = statement.getConnection();
                ResultSet resultSet = statement.getResultSet();

                if (connection != null) {
                    connection.close();
                }

                statement.close();

                if (resultSet != null) {
                    resultSet.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
