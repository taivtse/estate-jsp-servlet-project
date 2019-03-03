package com.laptrinhjavaweb.orm.util;

import com.laptrinhjavaweb.orm.annotation.Entity;

public interface EntityUtil {
    EntityUtilImpl ENTITY_UTIL = new EntityUtilImpl();

    String getTableName();

    String getIdColumnName();

    String getIdFieldName();

    String getColumnName(String fieldName);

    Object getIdFieldData(Object entity);

    static EntityUtil of(Class entityClass) {
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new RuntimeException(entityClass.getName() + " is not an entity");
        }

        ENTITY_UTIL.setEntityClass(entityClass);
        return ENTITY_UTIL;
    }
}
