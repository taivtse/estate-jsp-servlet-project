package com.laptrinhjavaweb.core.util;

public interface ModelAnnotationUtil {
    String getTableName();

    String getIdColumnName();

    boolean isAutoIncrement();

    String buildSelectStatement();

    String buildSelectByIdStatement();

    String buildInsertStatement();

    String buildUpdateByIdStatement();

    String buildDeleteByIdStatement();

    static ModelAnnotationUtil of(Class<?> modelClass){
        return new ModelAnnotationUtilImpl(modelClass);
    }
}
