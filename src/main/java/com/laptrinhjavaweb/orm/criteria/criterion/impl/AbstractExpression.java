package com.laptrinhjavaweb.orm.criteria.criterion.impl;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;

public abstract class AbstractExpression implements Criterion {
    protected StringBuilder fragment;
    protected String prefixLogical;


    public AbstractExpression(String prefixLogical) {
        this.fragment = new StringBuilder();
        this.prefixLogical = prefixLogical;

        if (!prefixLogical.isEmpty()) {
            this.prefixLogical = this.prefixLogical.concat(" ");
        }
    }

    @Override
    public String getPrefixLogical() {
        return prefixLogical;
    }

    @Override
    public void setPrefixLogical(String prefixLogical) {
        this.prefixLogical = prefixLogical;
    }

    abstract void buildFragment(Criteria criteria);

    @Override
    public String toSqlString(Criteria criteria) {
        this.buildFragment(criteria);
        return this.fragment.toString();
    }
}
