package com.laptrinhjavaweb.orm.builder;

import com.laptrinhjavaweb.orm.annotation.Entity;

public interface QueryBuilder {
    QueryBuilderImpl QUERY_BUILDER = new QueryBuilderImpl();

    String getTableName();

    String getIdColumnName();

    String getIdFieldName();

    Object getIdColumnValue(Object entity);

    boolean isAutoIncrement();

    String buildSelectQuery();

    String buildSelectByIdQuery();

    String buildInsertQuery();

    String buildUpdateQuery();

    String buildDeleteQuery();

    static QueryBuilder of(Class entityClass) throws Exception {
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new Exception(entityClass.getName() + " is not an entity");
        }

        QUERY_BUILDER.setEntityClass(entityClass);
        return QUERY_BUILDER;
    }
}
