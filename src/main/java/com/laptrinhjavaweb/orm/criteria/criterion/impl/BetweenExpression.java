package com.laptrinhjavaweb.orm.criteria.criterion.impl;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.statement.NamedParam;
import com.laptrinhjavaweb.orm.criteria.util.NamedParamHandlerUtil;
import com.laptrinhjavaweb.orm.util.EntityUtil;

public class BetweenExpression extends AbstractExpression implements Criterion {
    private final String propertyName;
    private final Object low;
    private final Object high;

    public BetweenExpression(String prefixLogical, String propertyName, Object low, Object high) {
        super(prefixLogical);
        this.propertyName = propertyName;
        this.low = low;
        this.high = high;
    }

    @Override
    public void buildFragment(Criteria criteria) {
//        lấy tên cột tương ứng với tên thuộc tính của entity
        String columnName = EntityUtil.of(criteria.getEntityClass()).getColumnName(propertyName);



        super.fragment.append(columnName);
        super.fragment.append(" BETWEEN");
        super.fragment.append(" {");
        super.fragment.append(namedParamLow);
        super.fragment.append("}");
        super.fragment.append(" AND");
        super.fragment.append(" {");
        super.fragment.append(namedParamHigh);
        super.fragment.append("}");
    }
}
