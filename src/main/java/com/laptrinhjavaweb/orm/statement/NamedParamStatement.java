package com.laptrinhjavaweb.orm.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NamedParamStatement {
    private PreparedStatement preparedStatement;
    private List<String> paramList = new ArrayList<>();

    public NamedParamStatement(Connection connection, String sql) throws SQLException {
        int pos;
        StringBuilder sqlBuilder = new StringBuilder(sql);
        while ((pos = sqlBuilder.indexOf("{")) != -1) {
            int end = sqlBuilder.indexOf("}", pos);
            paramList.add(sqlBuilder.substring(pos + 1, end));
            sqlBuilder.replace(pos, end + 1, "?");
        }
        this.preparedStatement = connection.prepareStatement(sqlBuilder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
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

    public void setParameter(String name, Object value) throws SQLException {
        int paramIndex = this.getIndex(name);
        if (paramIndex == -1) {
            throw new RuntimeException("Param " + name + " does not exists");
        }

        this.setParamAt(paramIndex, preparedStatement, value);
    }

    public void setNamedParamMap(Map<String, NamedParam> namedParamMap) throws SQLException {
        for (Map.Entry<String, NamedParam> entry : namedParamMap.entrySet()) {
            String key = entry.getKey();
            Object namedParamValue = entry.getValue().getValue();
            this.setParameter(key, namedParamValue);
        }
    }

    private int getIndex(String name) {
        return paramList.indexOf(name) + 1;
    }

    private static void setParamAt(int index, PreparedStatement preparedStatement, Object parameter) throws SQLException {
        preparedStatement.setObject(index, parameter);
    }
}
