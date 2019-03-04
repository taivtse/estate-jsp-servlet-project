package com.laptrinhjavaweb.orm.criteria.criterion.impl;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.criteria.util.NamedParamHandlerUtil;
import com.laptrinhjavaweb.orm.statement.NamedParam;
import com.laptrinhjavaweb.orm.util.EntityUtil;

public class SimpleExpression extends AbstractExpression implements Criterion {
    private final String propertyName;
    private final Object value;
    private final String op;

    public SimpleExpression(String prefixLogical, String propertyName, Object value, String op) {
        super(prefixLogical);
        this.propertyName = propertyName;
        this.value = value;
        this.op = op;
    }

    @Override
    public void buildFragment(Criteria criteria) {
//        lấy tên cột tương ứng với tên thuộc tính của entity
        String columnName = EntityUtil.getColumnName(criteria.getEntityClass(), propertyName);
        NamedParam namedParam = NamedParamHandlerUtil.createNewNamedParam(criteria.getNamedParamMap(), propertyName, value);
        super.namedParamMap.put(namedParam.getPropertyName(), namedParam.getValue());

//        tạo fragment
        super.fragment.append(columnName);
        super.fragment.append(" ");
        super.fragment.append(this.op);
        super.fragment.append(" {");
        super.fragment.append(namedParam);
        super.fragment.append("}");
    }
}
