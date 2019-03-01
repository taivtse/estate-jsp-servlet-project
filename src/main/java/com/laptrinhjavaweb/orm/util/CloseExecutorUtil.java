package com.laptrinhjavaweb.orm.util;

import com.laptrinhjavaweb.orm.criteria.NamedParamStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseExecutorUtil {
    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static void closeAllAfterExecute(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            closeConnection(connection);
            closeStatement(statement);
            closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeAllAfterExecute(NamedParamStatement statement, ResultSet resultSet) {
        try {
            closeConnection(statement.getConnection());
            closeStatement(statement.getPreparedStatement());
            closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
