package com.laptrinhjavaweb.orm.criteria;

import com.laptrinhjavaweb.orm.criteria.criterion.Order;
import com.laptrinhjavaweb.orm.criteria.criterion.Restriction;
import com.laptrinhjavaweb.orm.criteria.statement.NamedParam;

import java.util.List;
import java.util.Map;

public interface Criteria {
    Class getEntityClass();

    Map<String, NamedParam> getNamedParamMap();

    List list();

    Object uniqueResult();

    Criteria addSelection(String fieldName);

    Criteria addRestriction(Restriction restriction);

    Criteria addOrder(Order order);

    Criteria addProjection(String projection);

    Criteria setMaxResults(int limit);

    Criteria setFirstResult(int offset);
}
