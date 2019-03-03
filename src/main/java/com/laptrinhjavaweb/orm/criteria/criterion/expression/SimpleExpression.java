package com.laptrinhjavaweb.orm.criteria.criterion.expression;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.statement.NamedParam;
import com.laptrinhjavaweb.orm.criteria.util.NamedParamUtil;
import com.laptrinhjavaweb.orm.util.EntityUtil;

public class SimpleExpression implements Criterion {
    private String propertyName;
    private final Object value;
    private final String op;

    public SimpleExpression(String propertyName, Object value, String op) {
        this.propertyName = propertyName;
        this.value = value;
        this.op = op;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toSqlString(Criteria criteria) {
//        lấy tên cột tương ứng với tên thuộc tính của entity
        String columnName = EntityUtil.of(criteria.getEntityClass()).getColumnName(this.propertyName);

        NamedParam namedParam = NamedParamUtil.createNewNamedParam(criteria.getNamedParamMap(), propertyName, value);

        StringBuilder fragment = new StringBuilder(" ");
        fragment.append(columnName);
        fragment.append(" ");
        fragment.append(this.op);
        fragment.append(" {");
        fragment.append(namedParam);
        fragment.append("}");
        return fragment.toString();
    }
}
