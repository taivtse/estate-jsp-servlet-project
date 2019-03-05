package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.query.criteria.Criteria;
import com.laptrinhjavaweb.orm.query.sqlquery.SqlQuery;
import com.laptrinhjavaweb.orm.transaction.Transaction;

import java.sql.SQLException;

public interface Session {
    <T, ID> T get(Class<T> entityClass, ID id);

    <T> T save(T entity) throws SQLException;

    <T> T update(T entity) throws SQLException;

    <T> void delete(T entity) throws SQLException;

    SqlQuery createSQLQuery(String sql) throws SQLException;

    <T> Criteria createCriteria(Class<T> entityClass);

    Transaction beginTransaction();

    void close();
}
