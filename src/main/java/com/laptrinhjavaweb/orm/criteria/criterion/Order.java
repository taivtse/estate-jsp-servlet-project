package com.laptrinhjavaweb.orm.criteria.criterion;

public class Order {
    private String expression;

    public Order(String expression) {
        this.expression = expression;
    }

    public static Order asc(String propertyName) {
        return new Order(" ORDER BY " + propertyName + " ASC");
    }

    public static Order desc(String propertyName) {
        return new Order(" ORDER BY " + propertyName + " DESC");
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
