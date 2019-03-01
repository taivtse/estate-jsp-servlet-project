package com.laptrinhjavaweb.orm.builder;

import com.laptrinhjavaweb.orm.annotation.Column;
import com.laptrinhjavaweb.orm.annotation.Id;
import com.laptrinhjavaweb.orm.util.EntityUtil;

import java.lang.reflect.Field;

public class QueryBuilderImpl implements QueryBuilder {
    private Class<?> entityClass;

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public String buildSelectQuery(){
        StringBuilder statement = new StringBuilder("SELECT * FROM ");
        statement.append(EntityUtil.of(entityClass).getTableName());

        return statement.toString();
    }

    @Override
    public String buildSelectByIdQuery(){
        String idColumnName = EntityUtil.of(entityClass).getIdColumnName();
        String selectStatement = this.buildSelectQuery();

        StringBuilder statement = new StringBuilder(selectStatement);
        statement.append(" WHERE ");
        statement.append(idColumnName);
        statement.append(" = :");
        statement.append(EntityUtil.of(entityClass).getIdFieldName());

        return statement.toString();
    }

    @Override
    public String buildInsertQuery(){
        Field[] fieldList = this.entityClass.getDeclaredFields();

        StringBuilder statement = new StringBuilder("INSERT INTO ");
        statement.append(EntityUtil.of(entityClass).getTableName());

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
        statement.append(EntityUtil.of(entityClass).getTableName());
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
        statement.append(EntityUtil.of(entityClass).getIdColumnName());
        statement.append(" = :");
        statement.append(EntityUtil.of(entityClass).getIdFieldName());

        return statement.toString();
    }

    @Override
    public String buildDeleteQuery() {
        StringBuilder statement = new StringBuilder("DELETE FROM ");
        statement.append(EntityUtil.of(entityClass).getTableName());
        statement.append(" WHERE ");
        statement.append(EntityUtil.of(entityClass).getIdColumnName());
        statement.append(" = :");
        statement.append(EntityUtil.of(entityClass).getIdFieldName());

        return statement.toString();
    }
}
