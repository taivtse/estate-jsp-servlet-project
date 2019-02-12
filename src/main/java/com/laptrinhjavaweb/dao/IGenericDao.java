package com.laptrinhjavaweb.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, ID extends Serializable> {
    List<T> query(String sql, Object... parameters);

    void modifiedData(String sql, Object... parameters) throws Exception;

    Long save(T model) throws Exception;

    Long count(String sql, Object... parameters);

    //    common built methods
    List<T> findAll();

    T findById(ID id);
}
