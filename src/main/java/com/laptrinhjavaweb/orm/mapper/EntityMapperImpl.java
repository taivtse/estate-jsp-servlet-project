package com.laptrinhjavaweb.orm.mapper;

import com.laptrinhjavaweb.orm.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapperImpl implements EntityMapper {
    private Class<?> entityClass;

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Object toEntity(ResultSet resultSet) throws Exception {
        Object entity = this.entityClass.newInstance();
        Field[] fieldList = this.entityClass.getDeclaredFields();
        for (Field field : fieldList) {
//          get data from result set
            Object fieldData = this.getFieldDataFromResultSet(resultSet, field);

            String fieldName = field.getName();
//            upper case the first letter of field name
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

//            build setter method name
            String setterMethodName = "set" + fieldName;

//            get setter method and invoke
            Method setterMethod = this.entityClass.getMethod(setterMethodName, field.getType());
            setterMethod.invoke(entity, fieldData);
        }
        return entity;
    }

    private Object getFieldDataFromResultSet(ResultSet resultSet, Field field) throws Exception {
        String columnName = field.getAnnotation(Column.class).name();

        Object value = null;
        try {
            value = resultSet.getObject(columnName, field.getType());
        } catch (SQLException ignore) {
//            ignore columns we do not need to get
        }

        return value;
    }
}
