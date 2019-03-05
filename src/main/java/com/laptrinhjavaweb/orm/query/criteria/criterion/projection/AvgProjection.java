package com.laptrinhjavaweb.orm.query.criteria.criterion.projection;

public class AvgProjection extends AggregateProjection {
    public AvgProjection(String propertyName) {
        super("avg", propertyName);
    }
}
