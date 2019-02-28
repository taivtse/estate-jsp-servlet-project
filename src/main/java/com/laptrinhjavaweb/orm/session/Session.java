package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.criteria.Criteria;

import java.io.Serializable;
import java.util.List;

public interface Session {
    <T> List<T> findAll(Class<T> entityClass);

    <T, ID extends Serializable> T findOneById(Class<T> entityClass, ID id);

    <T> void save(T entity);

    <T> void update(T entity);

    <T> void delete(T entity);

    <T> Criteria createQuery(Class<T> entityClass);
}
