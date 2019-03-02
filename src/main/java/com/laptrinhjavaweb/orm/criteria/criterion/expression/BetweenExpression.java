package com.laptrinhjavaweb.orm.criteria.criterion.expression;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.criteria.statement.NamedParam;
import com.laptrinhjavaweb.orm.criteria.util.NamedParamUtil;
import com.laptrinhjavaweb.orm.util.EntityUtil;

public class BetweenExpression implements Criterion {
    private String propertyName;
    private final Object low;
    private final Object high;

    public BetweenExpression(String propertyName, Object low, Object high) {
        this.propertyName = propertyName;
        this.low = low;
        this.high = high;
    }

    public String toSqlString(Criteria criteria) {
//        lấy tên cột tương ứng với tên thuộc tính của entity
        String columnName = EntityUtil.of(criteria.getEntityClass()).getColumnName(this.propertyName);

        NamedParam namedParamLow = NamedParamUtil.createNewNamedParam(criteria.getNamedParamMap(), propertyName, low);

        NamedParam namedParamHigh = NamedParamUtil.createNewNamedParam(criteria.getNamedParamMap(), propertyName, high);

        StringBuilder fragment = new StringBuilder(" ");
        fragment.append(columnName);
        fragment.append(" BETWEEN");
        fragment.append(" :");
        fragment.append(namedParamLow);
        fragment.append(" AND");
        fragment.append(" :");
        fragment.append(namedParamHigh);
        return fragment.toString();
    }
}
