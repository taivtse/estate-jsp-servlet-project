package com.laptrinhjavaweb.orm.criteria.criterion;

import com.laptrinhjavaweb.orm.criteria.criterion.impl.BetweenExpression;
import com.laptrinhjavaweb.orm.criteria.criterion.impl.JunctionExpression;
import com.laptrinhjavaweb.orm.criteria.criterion.impl.SimpleExpression;

public class Restrictions {
    private String prefixLogical;
    private String propertyName;

    public Criterion eq(Object value) {
        return new SimpleExpression(this.prefixLogical, this.propertyName, value, "=");
    }

    public Criterion ne(Object value) {
        return new SimpleExpression(this.prefixLogical, this.propertyName, value, "<>");
    }

    public Criterion like(String value, MatchMode matchMode) {
        return new SimpleExpression(this.prefixLogical, propertyName, matchMode.toMatchString(value), "like");
    }

    public Criterion gt(Object value) {
        return new SimpleExpression(this.prefixLogical, this.propertyName, value, ">");
    }

    public Criterion lt(Object value) {
        return new SimpleExpression(this.prefixLogical, this.propertyName, value, "<");
    }

    public Criterion le(Object value) {
        return new SimpleExpression(this.prefixLogical, this.propertyName, value, "<=");
    }

    public Criterion ge(Object value) {
        return new SimpleExpression(this.prefixLogical, this.propertyName, value, ">=");
    }

    public Criterion between(String propertyName, Object low, Object hight) {
        return new BetweenExpression(this.prefixLogical, propertyName, low, hight);
    }

    public Criterion junction(Criterion... predicates) {
        return new JunctionExpression(this.prefixLogical, predicates);
    }

    public String getPrefixLogical() {
        return prefixLogical;
    }

    public void setPrefixLogical(String prefixLogical) {
        this.prefixLogical = prefixLogical;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
