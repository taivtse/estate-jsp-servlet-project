package com.laptrinhjavaweb.orm.util;

import com.laptrinhjavaweb.orm.annotation.Column;
import com.laptrinhjavaweb.orm.annotation.Entity;
import com.laptrinhjavaweb.orm.annotation.Id;
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
        String idFieldName = entityClass.getAnnotation(IdField.class).name();
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
    public Object getIdColumnValue(Object entity) {
//        String idColumnName = this.getIdColumnName();
//        try {
//            Field idField = entityClass.getDeclaredField(idColumnName);
//            boolean accessible = idField.isAccessible();
//            idField.setAccessible(true);
//
//            Object fieldData = idField.get(entity);
//            idField.setAccessible(accessible);
//
//            return fieldData;
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public boolean isAutoIncrement() {
        Field[] fieldList = this.entityClass.getDeclaredFields();

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getAnnotation(Id.class).autoIncrement();
            }
        }

        return false;
    }
}
