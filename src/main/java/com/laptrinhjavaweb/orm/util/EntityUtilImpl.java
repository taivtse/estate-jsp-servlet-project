package com.laptrinhjavaweb.orm.util;

import com.laptrinhjavaweb.orm.annotation.Column;
import com.laptrinhjavaweb.orm.annotation.Entity;
import com.laptrinhjavaweb.orm.annotation.IdField;

import java.lang.reflect.Field;

public class EntityUtilImpl implements EntityUtil {
    private Class<?> entityClass;

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public String getTableName() {
        return entityClass.getAnnotation(Entity.class).tableName();
    }

    @Override
    public String getIdColumnName() {
        String idFieldName = this.getIdFieldName();
        return this.getColumnName(idFieldName);
    }

    @Override
    public String getIdFieldName() {
        return entityClass.getAnnotation(IdField.class).name();
    }

    @Override
    public String getColumnName(String fieldName) {
        try {
            return entityClass.getDeclaredField(fieldName).getAnnotation(Column.class).name();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException("No field with name: " + fieldName);
        }
    }

    @Override
    public Object getIdFieldData(Object entity) {
        String idFieldName = this.getIdFieldName();
        try {
            Field idField = entityClass.getDeclaredField(idFieldName);
            boolean accessible = idField.isAccessible();
            idField.setAccessible(true);

            Object fieldData = idField.get(entity);
            idField.setAccessible(accessible);

            return fieldData;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
