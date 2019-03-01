package com.laptrinhjavaweb.orm.criteria;

import com.laptrinhjavaweb.orm.criteria.criterion.SimpleExpression;

import java.util.List;

public interface Criteria {
    List list();

    Object uniqueResult();

    Criteria addEntity(Class entityClass);

    Criteria addSelectField(String fieldName);

    Criteria addWhere(SimpleExpression expression);

    Criteria addOrder(String order);

    Criteria addProjection(String projection);

    Criteria setMaxResults(int limit);

    Criteria setFirstResult(int offset);
}
