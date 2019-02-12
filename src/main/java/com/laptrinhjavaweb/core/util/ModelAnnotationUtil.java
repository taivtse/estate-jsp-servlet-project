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

    static ModelAnnotationUtil of(Object model){
        return new ModelAnnotationUtilImpl(model);
    }
}
