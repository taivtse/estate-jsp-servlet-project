package com.laptrinhjavaweb.orm.criteria.criterion.impl;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.criteria.util.NamedParamHandlerUtil;
import com.laptrinhjavaweb.orm.statement.NamedParam;

public class PropertyExpression extends AbstractExpression implements Criterion {
    private final String propertyName;
    private final Object value;

    public PropertyExpression(String prefixLogical, String propertyName, Object value) {
        super(prefixLogical);
        this.propertyName = propertyName;
        this.value = value;
    }

    @Override
    public void buildFragment(Criteria criteria) {
        NamedParam namedParam = NamedParamHandlerUtil.createNewNamedParam(criteria.getNamedParamMap(), propertyName, value);
        criteria.getNamedParamMap().put(namedParam.getPropertyName(), namedParam);
    }
}
