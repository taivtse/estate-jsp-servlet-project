package com.laptrinhjavaweb.orm.query.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NativeSQLStatement {
    protected PreparedStatement preparedStatement;

    public NativeSQLStatement() {
    }

    public NativeSQLStatement(Connection connection, String sql) throws SQLException {
        this.preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public ResultSet executeQuery() throws SQLException {
        ResultSet resultSet = this.preparedStatement.executeQuery();
        return resultSet;
    }

    public Integer executeUpdate() throws SQLException {
        return this.preparedStatement.executeUpdate();
    }

    public Long executeInsert() throws SQLException {
        this.preparedStatement.executeUpdate();
        ResultSet resultSet = this.preparedStatement.getGeneratedKeys();

        if (resultSet != null && resultSet.next()) {
            return resultSet.getLong(1);
        }

        return null;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setParamAt(int index, Object parameter) throws SQLException {
        preparedStatement.setObject(index, parameter);
    }
}
