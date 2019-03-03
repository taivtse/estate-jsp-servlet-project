package com.laptrinhjavaweb.orm.builder;

import com.laptrinhjavaweb.orm.annotation.Entity;

public interface StatementBuilder {
    StatementBuilderImpl STATEMENT_BUILDER = new StatementBuilderImpl();

    String buildSelectQuery();

    String buildSelectByIdQuery();

    String buildInsertStatement();

    String buildUpdateStatement();

    String buildDeleteStatement();

    static StatementBuilder of(Class entityClass) {
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new RuntimeException(entityClass.getName() + " is not an entity");
        }

        STATEMENT_BUILDER.setEntityClass(entityClass);
        return STATEMENT_BUILDER;
    }
}
