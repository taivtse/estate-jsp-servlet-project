package com.laptrinhjavaweb.orm.criteria;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NamedParamQuery {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private List<String> fields = new ArrayList<>();

    public NamedParamQuery(Connection connection) {
        this.connection = connection;
    }

    public void setSqlQuery(String sql) throws SQLException {
        int pos;
        while ((pos = sql.indexOf(":")) != -1) {
            int end = sql.substring(pos).indexOf(" ");
            if (end == -1)
                end = sql.length();
            else
                end += pos;
            fields.add(sql.substring(pos + 1, end));
            sql = sql.substring(0, pos) + "?" + sql.substring(end);
        }
        this.preparedStatement = this.connection.prepareStatement(sql);
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setParam(String name, Object value) throws Exception {
        int paramIndex = this.getIndex(name);
        if (paramIndex == -1) {
            throw new Exception("Param " + name + " does not exists");
        }
        this.setParamAt(paramIndex, preparedStatement, value);
    }

    private int getIndex(String name) {
        return fields.indexOf(name) + 1;
    }

    private static void setParamAt(int index, PreparedStatement preparedStatement, Object parameter) throws Exception {
        if (parameter instanceof Byte) {
            preparedStatement.setByte(index, (Byte) parameter);

        } else if (parameter instanceof Short) {
            preparedStatement.setShort(index, (Short) parameter);

        } else if (parameter instanceof Integer) {
            preparedStatement.setInt(index, (Integer) parameter);

        } else if (parameter instanceof Long) {
            preparedStatement.setLong(index, (Long) parameter);

        } else if (parameter instanceof Float) {
            preparedStatement.setFloat(index, (Float) parameter);

        } else if (parameter instanceof Double) {
            preparedStatement.setDouble(index, (Double) parameter);

        } else if (parameter instanceof BigDecimal) {
            preparedStatement.setBigDecimal(index, (BigDecimal) parameter);

        } else if (parameter instanceof String) {
            preparedStatement.setString(index, (String) parameter);

        } else if (parameter instanceof Date) {
            preparedStatement.setDate(index, (Date) parameter);

        } else if (parameter instanceof Time) {
            preparedStatement.setTime(index, (Time) parameter);

        } else if (parameter instanceof Timestamp) {
            preparedStatement.setTimestamp(index, (Timestamp) parameter);

        } else if (parameter == null) {
            preparedStatement.setNull(index, Types.NULL);
        } else {
            throw new Exception("Chưa hỗ trợ parameter có kiểu: " + parameter.getClass().getName());
        }
    }

}
