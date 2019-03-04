package com.laptrinhjavaweb.orm.criteria;

import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.criteria.criterion.Order;
import com.laptrinhjavaweb.orm.statement.NamedParam;

import java.util.List;
import java.util.Map;

public interface Criteria {
    Class getEntityClass();

    Map<String, NamedParam> getNamedParamMap();

    List list();

    Object uniqueResult();

    Criteria addSelection(String fieldName);

    Criteria add(Criterion criterion);

    Criteria addOrder(Order order);

    Criteria addProjection(String projection);

    Criteria setMaxResults(int limit);

    Criteria setFirstResult(int offset);
}
