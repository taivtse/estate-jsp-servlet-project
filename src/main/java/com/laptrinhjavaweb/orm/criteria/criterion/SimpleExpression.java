package com.laptrinhjavaweb.orm.criteria.criterion;

public class SimpleExpression {
    private String propertyName;
    private Object value;
    private String op;
    private String columnName;

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String toSqlString() {
        StringBuilder fragment = new StringBuilder();
        fragment.append("( ");
        fragment.append(this.columnName);
        fragment.append(" ");
        fragment.append(this.op);
        fragment.append(" :");
        fragment.append(this.propertyName);
        fragment.append(" )");
        return fragment.toString();
    }
}
