package com.laptrinhjavaweb.orm.criteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NamedParamStatement {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private List<String> paramList = new ArrayList<>();

    public NamedParamStatement(Connection connection) {
        this.connection = connection;
    }

    public void setSqlStatement(String sql) throws SQLException {
        int pos;
        while ((pos = sql.indexOf(":")) != -1) {
            int end = sql.substring(pos).indexOf(" ");
            if (end == -1)
                end = sql.length();
            else
                end += pos;
            paramList.add(sql.substring(pos + 1, end));
            sql = sql.substring(0, pos) + "?" + sql.substring(end);
        }
        this.preparedStatement = this.connection.prepareStatement(sql);
    }

    public ResultSet executeQuery() throws SQLException {
        ResultSet resultSet = this.preparedStatement.executeQuery();
        return resultSet;
    }

    public Integer executeUpdate() throws SQLException {
        Integer rowEffect = this.preparedStatement.executeUpdate();
        return rowEffect;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setParam(String name, Object value) {
        int paramIndex = this.getIndex(name);
        if (paramIndex == -1) {
            throw new RuntimeException("Param " + name + " does not exists");
        }
        try {
            this.setParamAt(paramIndex, preparedStatement, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getIndex(String name) {
        return paramList.indexOf(name) + 1;
    }

    private static void setParamAt(int index, PreparedStatement preparedStatement, Object parameter) throws SQLException {
        preparedStatement.setObject(index, parameter);
    }

}
