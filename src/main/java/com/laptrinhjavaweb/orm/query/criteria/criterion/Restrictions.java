package com.laptrinhjavaweb.orm.query.criteria.criterion;

import com.laptrinhjavaweb.orm.query.criteria.criterion.impl.BetweenExpression;
import com.laptrinhjavaweb.orm.query.criteria.criterion.impl.SimpleExpression;

public class Restrictions {
    private String expressionPrefixLogical;
    private String expressionPropertyName;

    public Restrictions(String expressionPrefixLogical) {
        this.expressionPrefixLogical = expressionPrefixLogical;
    }

    public Restrictions(String prefixLogical, String expressionPropertyName) {
        this.expressionPrefixLogical = prefixLogical;
        this.expressionPropertyName = expressionPropertyName;
    }

    public Criterion eq(Object value) {
        return new SimpleExpression(this.expressionPrefixLogical, this.expressionPropertyName, value, "=");
    }

    public Criterion ne(Object value) {
        return new SimpleExpression(this.expressionPrefixLogical, this.expressionPropertyName, value, "<>");
    }

    public Criterion like(String value, MatchMode matchMode) {
        return new SimpleExpression(this.expressionPrefixLogical, expressionPropertyName, matchMode.toMatchString(value), "like");
    }

    public Criterion gt(Object value) {
        return new SimpleExpression(this.expressionPrefixLogical, this.expressionPropertyName, value, ">");
    }

    public Criterion lt(Object value) {
        return new SimpleExpression(this.expressionPrefixLogical, this.expressionPropertyName, value, "<");
    }

    public Criterion le(Object value) {
        return new SimpleExpression(this.expressionPrefixLogical, this.expressionPropertyName, value, "<=");
    }

    public Criterion ge(Object value) {
        return new SimpleExpression(this.expressionPrefixLogical, this.expressionPropertyName, value, ">=");
    }

    public Criterion between(Object low, Object high) {
        return new BetweenExpression(this.expressionPrefixLogical, this.expressionPropertyName, low, high);
    }
}
