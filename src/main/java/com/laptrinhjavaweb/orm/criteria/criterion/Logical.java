package com.laptrinhjavaweb.orm.criteria.criterion;

import com.laptrinhjavaweb.orm.criteria.criterion.impl.JunctionExpression;

public class Logical {
    private static final String prefixAnd = " AND";
    private static final String prefixOr = " OR";

    public static Restrictions and(String propertyName) {
        return new Restrictions(Logical.prefixAnd, propertyName);
    }

    public static Restrictions or(String propertyName) {
        return new Restrictions(Logical.prefixOr, propertyName);
    }

    public static Restrictions noPrefix(String propertyName) {
        return new Restrictions(Logical.prefixAnd, propertyName);
    }

    public static JunctionExpression andJunction() {
        return new JunctionExpression(Logical.prefixAnd);
    }

    public static JunctionExpression orJunction() {
        return new JunctionExpression(Logical.prefixOr);
    }

    public static JunctionExpression noPrefixJunction() {
        return new JunctionExpression("");
    }
}
