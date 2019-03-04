package com.laptrinhjavaweb.orm.criteria.criterion;

import com.laptrinhjavaweb.orm.criteria.Criteria;

public interface Criterion {
    String getPrefixLogical();

    void setPrefixLogical(String prefixLogical);

    String toSqlString(Criteria criteria);
}