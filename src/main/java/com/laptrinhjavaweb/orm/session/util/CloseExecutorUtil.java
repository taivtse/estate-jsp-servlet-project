package com.laptrinhjavaweb.orm.session.util;

import com.laptrinhjavaweb.orm.statement.NamedParamStatement;

import java.sql.*;

public class CloseExecutorUtil {
    public static void closeStatement(Statement statement) throws SQLException {
        if (statement != null && !statement.isClosed()) {
            ResultSet resultSet = statement.getResultSet();

            if (resultSet != null) {
                resultSet.close();
            }

            statement.close();
        }
    }

    public static void closeNamedParamStatement(NamedParamStatement namedParamStatement) throws SQLException {
        closeStatement(namedParamStatement.getPreparedStatement());
    }

    public static void closeAllAfterExecute(Statement statement) throws SQLException {
        Connection connection = statement.getConnection();
        closeStatement(statement);
        connection.close();
    }

    public static void closeAllAfterExecute(NamedParamStatement namedParamStatement) throws SQLException {
        Statement statement = namedParamStatement.getPreparedStatement();
        closeAllAfterExecute(statement);
    }
}
