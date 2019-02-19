package com.laptrinhjavaweb.orm.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;

public class StatementUtil {

    public static void setParametersToStatement(PreparedStatement preparedStatement, Object... parameters) throws Exception {
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            int index = i + 1;

            StatementUtil.setParameterAt(index, preparedStatement, parameter);
        }
    }

    public static void setEntityToStatement(PreparedStatement preparedStatement, Object model) throws Exception {
        Field[] fieldList = model.getClass().getDeclaredFields();
        int index = 1;
        for (Field field : fieldList) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
//          set data form entity to preparedStatement
            Object fieldData = field.get(model);
            field.setAccessible(accessible);

            StatementUtil.setParameterAt(index, preparedStatement, fieldData);
            index++;
        }
    }

    private static void setParameterAt(int index, PreparedStatement preparedStatement, Object parameter) throws Exception {
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

        } else if (parameter instanceof InputStream) {
            preparedStatement.setBlob(index, (InputStream) parameter);

        } else if (parameter == null) {
            preparedStatement.setNull(index, Types.NULL);
        } else {
            throw new Exception("Chưa hỗ trợ parameter có kiểu: " + parameter.getClass().getName());
        }
    }
}
