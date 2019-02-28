package com.laptrinhjavaweb.orm.builder;

import com.laptrinhjavaweb.orm.annotation.Column;
import com.laptrinhjavaweb.orm.annotation.Entity;
import com.laptrinhjavaweb.orm.annotation.Id;
import com.laptrinhjavaweb.orm.annotation.IdField;

import java.lang.reflect.Field;

public class QueryBuilderImpl implements QueryBuilder {
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

        try {
            Field idField = entityClass.getDeclaredField(idFieldName);

            return idField.getAnnotation(Column.class).name();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getIdFieldName() {
        return entityClass.getAnnotation(IdField.class).name();
    }

    @Override
    public Object getIdColumnValue(Object entity) {
        String idColumnName = this.getIdColumnName();
        try {
            Field idField = entityClass.getDeclaredField(idColumnName);
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

    @Override
    public String buildSelectQuery() {
        StringBuilder statement = new StringBuilder("SELECT * FROM ");
        statement.append(this.getTableName());

        return statement.toString();
    }

    @Override
    public String buildSelectByIdQuery() {
        String idColumnName = this.getIdColumnName();
        String selectStatement = this.buildSelectQuery();

        StringBuilder statement = new StringBuilder(selectStatement);
        statement.append(" WHERE ");
        statement.append(idColumnName);
        statement.append(" = :");
        statement.append(this.getIdFieldName());

        return statement.toString();
    }

    @Override
    public String buildInsertQuery() {
        Field[] fieldList = this.entityClass.getDeclaredFields();

        StringBuilder statement = new StringBuilder("INSERT INTO ");
        statement.append(this.getTableName());

        statement.append("(");
        for (int i = 0; i < fieldList.length; i++) {
            statement.append(fieldList[i].getAnnotation(Column.class).name());

            if (i < fieldList.length - 1) {
                statement.append(", ");
            }
        }

        statement.append(") VALUES(");
        for (int i = 0; i < fieldList.length; i++) {
            statement.append(":");
            statement.append(fieldList[i].getName());

            if (i < fieldList.length - 1) {
                statement.append(", ");
            }
        }

        statement.append(")");

        return statement.toString();
    }

    @Override
    public String buildUpdateQuery() {
        StringBuilder statement = new StringBuilder("UPDATE ");
        statement.append(this.getTableName());
        statement.append(" SET ");

        Field[] fieldList = this.entityClass.getDeclaredFields();
        for (int i = 0; i < fieldList.length; i++) {
//            skip id column
            if (fieldList[i].isAnnotationPresent(Id.class)) {
                continue;
            }

            String columnName = fieldList[i].getAnnotation(Column.class).name();
            statement.append(columnName);
            statement.append(" = :");
            statement.append(fieldList[i].getName());

            if (i < fieldList.length - 1) {
                statement.append(", ");
            }
        }

        statement.append(" WHERE ");
        statement.append(this.getIdColumnName());
        statement.append(" = :");
        statement.append(this.getIdFieldName());

        return statement.toString();
    }

    @Override
    public String buildDeleteQuery() {
        StringBuilder statement = new StringBuilder("DELETE FROM ");
        statement.append(this.getTableName());
        statement.append(" WHERE ");
        statement.append(this.getIdColumnName());
        statement.append(" = :");
        statement.append(this.getIdFieldName());

        return statement.toString();
    }
}
