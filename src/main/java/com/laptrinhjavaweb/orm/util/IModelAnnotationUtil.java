package com.laptrinhjavaweb.orm.util;

public interface IModelAnnotationUtil {
    String getTableName();

    String getIdColumnName();

    boolean isAutoIncrement();

    String buildSelectStatement();

    String buildSelectByIdStatement();

    String buildInsertStatement();

    String buildUpdateByIdStatement();

    String buildDeleteByIdStatement();

    static IModelAnnotationUtil of(Class<?> modelClass){
        return new ModelAnnotationUtilImpl(modelClass);
    }
}
