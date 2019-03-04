package com.laptrinhjavaweb.orm.criteria.criterion;

import com.laptrinhjavaweb.orm.criteria.Criteria;

import java.util.Map;

public interface Criterion {
    Map<String, Object> getNamedParamMap();

    String getPrefixLogical();

    void setPrefixLogical(String prefixLogical);

    void buildFragment(Criteria criteria);

    String toSqlString();
}