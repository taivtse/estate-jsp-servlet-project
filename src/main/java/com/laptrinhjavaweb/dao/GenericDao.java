package com.laptrinhjavaweb.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    List<T> findAll();

//    List<T> findByProperties(List<>);

    T findOneById(ID id);
}
