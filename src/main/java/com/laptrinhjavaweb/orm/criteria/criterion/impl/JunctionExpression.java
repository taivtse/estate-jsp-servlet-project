package com.laptrinhjavaweb.orm.criteria.criterion.impl;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;

import java.util.ArrayList;
import java.util.List;

public class JunctionExpression extends AbstractExpression implements Criterion {
    private final List<Criterion> criterionList = new ArrayList<>();

    public JunctionExpression(String prefixLogical) {
        super(prefixLogical);
    }

    public JunctionExpression add(Criterion criterion) {
        criterionList.add(criterion);
        return this;
    }

    @Override
    public void buildFragment(Criteria criteria) {
//        tạo fragment
        super.fragment.append(super.prefixLogical);
        super.fragment.append("(");
        for (int i = 0; i < criterionList.size(); i++) {
            if (i == 0) {
                criterionList.get(i).setPrefixLogical("");
            }

            super.fragment.append(criterionList.get(i).toSqlString(criteria));
        }

        super.fragment.append(")");
    }
}