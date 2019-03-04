package com.laptrinhjavaweb.orm.criteria.criterion;

public class Logical {
    private static final String prefixAnd = " AND";
    private static final String prefixOr = " OR";

    public static Restrictions and(String propertyName) {
        return new Restrictions(Logical.prefixAnd, propertyName);
    }

    public static Restrictions or(String propertyName) {
        return new Restrictions(Logical.prefixOr, propertyName);
    }
}
