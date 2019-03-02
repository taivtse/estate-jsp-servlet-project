package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.criteria.Criteria;

public interface Session {
    <T, ID> T get(Class<T> entityClass, ID id);

    <T> void save(T entity);

    <T> void update(T entity);

    <T> void delete(T entity);

    <T> Criteria createCriteria(Class<T> entityClass);
}
