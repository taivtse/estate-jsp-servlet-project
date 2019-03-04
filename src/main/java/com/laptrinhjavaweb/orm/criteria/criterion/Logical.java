package com.laptrinhjavaweb.orm.criteria.criterion;

public class Logical {
    private static final Restrictions RESTRICTIONS = new Restrictions();

    public static Restrictions and(String propertyName) {
        RESTRICTIONS.setPrefixLogical(" AND");
        RESTRICTIONS.setPropertyName(propertyName);
        return RESTRICTIONS;
    }

    public static Restrictions or(String propertyName) {
        RESTRICTIONS.setPrefixLogical(" OR");
        RESTRICTIONS.setPropertyName(propertyName);
        return RESTRICTIONS;
    }
}
