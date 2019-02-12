package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.core.anotation.Column;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;

public class RowMapperImpl implements IRowMapper {
    private Class<?> modelClass;

    public RowMapperImpl(Class<?> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public Object mapRow(ResultSet resultSet) throws Exception {
        Object obj = this.modelClass.newInstance();
        Field[] fieldList = this.modelClass.getDeclaredFields();
        for (Field field : fieldList) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);

//          get data from rs and set to obj
            Object fieldData = this.getFieldDataFromResultSet(resultSet, field);
            field.set(obj, fieldData);

            field.setAccessible(accessible);
        }
        return obj;
    }

    private Object getFieldDataFromResultSet(ResultSet resultSet, Field field) throws Exception {
        String columnName = field.getAnnotation(Column.class).name();

        if (field.getType().isAssignableFrom(Byte.class)) {
            return resultSet.getByte(columnName);

        } else if (field.getType().isAssignableFrom(Short.class)) {
            return resultSet.getShort(columnName);

        } else if (field.getType().isAssignableFrom(Integer.class)) {
            return resultSet.getInt(columnName);

        } else if (field.getType().isAssignableFrom(Long.class)) {
            return resultSet.getLong(columnName);

        } else if (field.getType().isAssignableFrom(Float.class)) {
            return resultSet.getFloat(columnName);

        } else if (field.getType().isAssignableFrom(Double.class)) {
            return resultSet.getDouble(columnName);

        } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
            return resultSet.getBigDecimal(columnName);

        } else if (field.getType().isAssignableFrom(String.class)) {
            return resultSet.getString(columnName);

        } else if (field.getType().isAssignableFrom(Date.class)) {
            return resultSet.getDate(columnName);

        } else if (field.getType().isAssignableFrom(Time.class)) {
            return resultSet.getTime(columnName);

        } else if (field.getType().isAssignableFrom(Timestamp.class)) {
            return resultSet.getTimestamp(columnName);

        } else if (field.getType().isAssignableFrom(InputStream.class)) {
            return resultSet.getBlob(columnName).getBinaryStream();

        } else {
            throw new Exception("Chưa hỗ trợ field có kiểu: " + field.getClass().getName());
        }
    }
}
