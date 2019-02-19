package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.orm.annotation.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;

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

        return resultSet.getObject(columnName, field.getType());
    }
}
