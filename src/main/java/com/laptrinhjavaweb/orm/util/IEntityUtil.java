package com.laptrinhjavaweb.orm.util;

public interface IEntityUtil {
    String getTableName();

    String getIdColumnName();

    boolean isAutoIncrement();

    String buildSelectStatement();

    String buildSelectByIdStatement();

    String buildInsertStatement();

    String buildUpdateByIdStatement();

    String buildDeleteByIdStatement();

    static IEntityUtil of(Class<?> modelClass){
        return new EntityUtilImpl(modelClass);
    }
}
