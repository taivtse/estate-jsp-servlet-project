package com.laptrinhjavaweb.orm.mapper;

import com.laptrinhjavaweb.orm.annotation.Column;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;

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

//            build setter method name
            String fieldName = field.getName();
            String setterMethodName = "set" + StringUtils.capitalize(fieldName);

//            get setter method and invoke
            Method setterMethod = this.entityClass.getMethod(setterMethodName, field.getType());
            setterMethod.invoke(entity, fieldData);
        }
        return entity;
    }

    private Object getFieldDataFromResultSet(ResultSet resultSet, Field field) throws Exception {
        String columnName = field.getAnnotation(Column.class).name();

        return resultSet.getObject(columnName, field.getType());
    }
}
