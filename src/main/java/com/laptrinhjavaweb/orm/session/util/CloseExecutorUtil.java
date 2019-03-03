package com.laptrinhjavaweb.orm.session.util;

import com.laptrinhjavaweb.orm.statement.NamedParamStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
