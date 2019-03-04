package com.laptrinhjavaweb.orm.criteria.criterion.impl;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;

public class JunctionExpression extends AbstractExpression implements Criterion {
    private final Criterion[] criterionArray;

    public JunctionExpression(String prefixLogical, Criterion... criterionArray) {
        super(prefixLogical);
        this.criterionArray = criterionArray;
    }

    @Override
    public void buildFragment(Criteria criteria) {
//        tạo fragment
        super.fragment.append("(");
        for (Criterion criterion : criterionArray) {
            criterion.buildFragment(criteria);
            super.namedParamMap.putAll(criterion.getNamedParamMap());

            super.fragment.append(criterion.toSqlString());
        }

        super.fragment.append(")");
    }
}
