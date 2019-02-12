package com.laptrinhjavaweb.core.util;

import com.laptrinhjavaweb.core.anotation.Column;
import com.laptrinhjavaweb.core.anotation.Entity;
import com.laptrinhjavaweb.core.anotation.GeneratedValue;
import com.laptrinhjavaweb.core.anotation.Id;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModelAnnotationUtilImpl implements ModelAnnotationUtil {
    private Object model;

    public ModelAnnotationUtilImpl(Object model) {
        this.model = model;
    }

    @Override
    public String getTableName() {
        return model.getClass().getAnnotation(Entity.class).tableName();
    }

    @Override
    public String getIdColumnName() {
        Field[] fieldList = this.model.getClass().getDeclaredFields();

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getAnnotation(Column.class).name();
            }
        }

        return null;
    }

    @Override
    public boolean isAutoIncrement() {
        Field[] fieldList = this.model.getClass().getDeclaredFields();

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getAnnotation(GeneratedValue.class).autoIncrement();
            }
        }

        return false;
    }

    @Override
    public String buildInsertStatement() {
        StringBuilder statement = new StringBuilder("INSERT INTO ");

        statement.append(this.getTableName());
        statement.append(" VALUES(");

        int fieldsCount = this.model.getClass().getDeclaredFields().length;
        for (int i = 0; i < fieldsCount; i++) {
            statement.append("?");

            if (i < fieldsCount - 1) {
                statement.append(",");
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

        Field[] fieldList = this.model.getClass().getDeclaredFields();
        for (int i = 0; i < fieldList.length; i++) {
//            skip id column
            if (fieldList[i].isAnnotationPresent(Id.class)) {
                continue;
            }

            String columnName = fieldList[i].getAnnotation(Column.class).name();
            statement.append(columnName);
            statement.append(" = ?");

            if (i < fieldList.length - 1) {
                statement.append(",");
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
