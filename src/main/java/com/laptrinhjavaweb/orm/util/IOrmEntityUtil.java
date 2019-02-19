package com.laptrinhjavaweb.orm.util;

public interface IOrmEntityUtil {
    String getTableName();

    String getIdColumnName();

    boolean isAutoIncrement();

    String buildSelectStatement();

    String buildSelectByIdStatement();

    String buildInsertStatement();

    String buildUpdateByIdStatement();

    String buildDeleteByIdStatement();

    static IOrmEntityUtil of(Class<?> modelClass){
        return new OrmEntityUtilImpl(modelClass);
    }
}
