package com.laptrinhjavaweb.orm.query.sqlquery;

import java.sql.ResultSet;

public interface SqlQuery {
    ResultSet list();

    int executeUpdate();

    void setParameter(int index, Object parameter);

    void setParameter(String namedParam, Object parameter);
}
