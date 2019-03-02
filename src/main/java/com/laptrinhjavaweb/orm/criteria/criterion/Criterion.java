package com.laptrinhjavaweb.orm.criteria.criterion;

import com.laptrinhjavaweb.orm.criteria.Criteria;

public interface Criterion {
    String toSqlString(Criteria criteria);
}