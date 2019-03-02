package com.laptrinhjavaweb.orm.criteria.criterion;

import com.laptrinhjavaweb.orm.criteria.criterion.expression.BetweenExpression;
import com.laptrinhjavaweb.orm.criteria.criterion.expression.SimpleExpression;

public class Restriction {
    private static final Restriction RESTRICTION = new Restriction();
    private String prefixLogical;
    private Criterion criterion;

    public static Restriction and() {
        RESTRICTION.prefixLogical = " AND";
        return RESTRICTION;
    }

    public static Restriction or() {
        RESTRICTION.prefixLogical = " OR";
        return RESTRICTION;
    }

    public Restriction eq(String propertyName, Object value) {
        this.criterion = new SimpleExpression(propertyName, value, "=");
        return this;
    }

    public Restriction ne(String propertyName, Object value) {
        this.criterion = new SimpleExpression(propertyName, value, "<>");
        return this;
    }

    public Restriction like(String propertyName, String value, MatchMode matchMode) {
        this.criterion = new SimpleExpression(propertyName, matchMode.toMatchString(value), "like");
        return this;
    }

    public Restriction gt(String propertyName, Object value) {
        this.criterion = new SimpleExpression(propertyName, value, ">");
        return this;
    }

    public Restriction lt(String propertyName, Object value) {
        this.criterion = new SimpleExpression(propertyName, value, "<");
        return this;
    }

    public Restriction le(String propertyName, Object value) {
        this.criterion = new SimpleExpression(propertyName, value, "<=");
        return this;
    }

    public Restriction ge(String propertyName, Object value) {
        this.criterion = new SimpleExpression(propertyName, value, ">=");
        return this;
    }

    public Restriction between(String propertyName, Object low, Object hight) {
        this.criterion = new BetweenExpression(propertyName, low, hight);
        return this;
    }

    // getter setter
    public String getPrefixLogical() {
        return prefixLogical;
    }

    public void setPrefixLogical(String prefixLogical) {
        this.prefixLogical = prefixLogical;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }
}
