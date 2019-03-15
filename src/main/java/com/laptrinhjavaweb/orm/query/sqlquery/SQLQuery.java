package com.laptrinhjavaweb.orm.query.sqlquery;

import java.sql.SQLException;
import java.util.List;

public interface SQLQuery {
    List list();

    int executeUpdate() throws SQLException;

    void setParameter(int index, Object parameter) throws SQLException;

    void setParameter(String namedParam, Object parameter) throws SQLException;

    SQLQuery setEntity(Class entityClass);
}
