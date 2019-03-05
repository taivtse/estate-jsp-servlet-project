package com.laptrinhjavaweb.orm.query.criteria.criterion.projection;

import com.laptrinhjavaweb.orm.query.criteria.Criteria;

public class RowCountProjection implements Projection {
    @Override
    public String toSqlString(Criteria criteria) {
        return "count(*)";
    }
}
