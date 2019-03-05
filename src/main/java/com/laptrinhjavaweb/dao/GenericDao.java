package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.paging.Pageable;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, ID> {
    List<T> findAll();

    List<T> findAllByProperties(Pageable pageable, List<Criterion> criterionList);

    Long countByProperties(List<Criterion> criterionList);

    T findOneById(ID id);

    T findOneByProperties(List<Criterion> criterionList);

    T save(T entity) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(T entity) throws SQLException;

    void deleteById(ID id) throws SQLException;
}
