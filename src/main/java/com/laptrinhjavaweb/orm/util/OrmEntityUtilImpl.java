package com.laptrinhjavaweb.orm.util;

import com.laptrinhjavaweb.orm.annotation.Column;
import com.laptrinhjavaweb.orm.annotation.Entity;
import com.laptrinhjavaweb.orm.annotation.Id;

import java.lang.reflect.Field;

public class OrmEntityUtilImpl implements IOrmEntityUtil {
    private Class<?> modelClass;

    public OrmEntityUtilImpl(Class<?> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public String getTableName() {
        return this.modelClass.getAnnotation(Entity.class).tableName();
    }

    @Override
    public String getIdColumnName() {
        Field[] fieldList = this.modelClass.getDeclaredFields();

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getAnnotation(Column.class).name();
            }
        }

        return null;
    }

    @Override
    public boolean isAutoIncrement() {
        Field[] fieldList = this.modelClass.getDeclaredFields();

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getAnnotation(Id.class).autoIncrement();
            }
        }

        return false;
    }

    @Override
    public String buildInsertStatement() {
        Field[] fieldList = this.modelClass.getDeclaredFields();

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
            statement.append("?");

            if (i < fieldList.length - 1) {
                statement.append(", ");
            }
        }

        statement.append(")");

        return statement.toString();
    }

    @Override
    public String buildUpdateByIdStatement() {
        StringBuilder statement = new StringBuilder("UPDATE ");
        statement.append(this.getTableName());
        statement.append(" SET ");

        Field[] fieldList = this.modelClass.getDeclaredFields();
        for (int i = 0; i < fieldList.length; i++) {
//            skip id column
            if (fieldList[i].isAnnotationPresent(Id.class)) {
                continue;
            }

            String columnName = fieldList[i].getAnnotation(Column.class).name();
            statement.append(columnName);
            statement.append(" = ?");

            if (i < fieldList.length - 1) {
                statement.append(", ");
            }
        }

        statement.append(" WHERE ");
        statement.append(this.getIdColumnName());
        statement.append(" = ?");

        return statement.toString();
    }

    @Override
    public String buildSelectStatement() {
        StringBuilder statement = new StringBuilder("SELECT * FROM ");
        statement.append(this.getTableName());

        return statement.toString();
    }

    @Override
    public String buildSelectByIdStatement() {
        String idColumnName = this.getIdColumnName();
        String selectStatement = this.buildSelectStatement();

        StringBuilder statement = new StringBuilder(selectStatement);
        statement.append(" WHERE ");
        statement.append(idColumnName);
        statement.append(" = ?");

        return statement.toString();
    }

    @Override
    public String buildDeleteByIdStatement() {
        String idColumnName = this.getIdColumnName();
        String tableName = this.getTableName();

        StringBuilder statement = new StringBuilder("DELETE FROM ");
        statement.append(tableName);
        statement.append(" WHERE ");
        statement.append(idColumnName);
        statement.append(" = ?");

        return statement.toString();
    }
}
