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
        super.fragment.append(super.prefixLogical);
        super.fragment.append("(");
        for (int i = 0; i < criterionArray.length; i++) {
            if (i == 0) {
                criterionArray[i].setPrefixLogical("");
            }

            super.fragment.append(criterionArray[i].toSqlString(criteria));
        }

        super.fragment.append(")");
    }
}
