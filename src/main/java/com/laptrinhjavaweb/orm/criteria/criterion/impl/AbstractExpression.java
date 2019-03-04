package com.laptrinhjavaweb.orm.criteria.criterion.impl;

import com.laptrinhjavaweb.orm.criteria.criterion.Criterion;

import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractExpression implements Criterion {
    protected Map<String, Object> namedParamMap = new TreeMap<>();
    protected StringBuilder fragment;
    protected String prefixLogical;


    public AbstractExpression(String prefixLogical) {
        this.fragment = new StringBuilder();
        this.prefixLogical = prefixLogical;

        if (prefixLogical.isEmpty()) {
            this.fragment.append(prefixLogical);
            this.fragment.append(" ");
        }
    }

    @Override
    public Map<String, Object> getNamedParamMap() {
        return namedParamMap;
    }

    @Override
    public String getPrefixLogical() {
        return prefixLogical;
    }

    @Override
    public void setPrefixLogical(String prefixLogical) {
        this.prefixLogical = prefixLogical;
    }

    @Override
    public String toSqlString() {
        return this.fragment.toString();
    }

    @Override
    public String toString() {
        return this.fragment.toString();
    }
}
