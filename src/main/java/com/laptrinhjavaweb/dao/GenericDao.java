package com.laptrinhjavaweb.dao;

import java.util.List;

public interface GenericDao<T, ID> {
    List<T> findAll();

//    List<T> findByProperties(List<>);

    T findOneById(ID id);
}
