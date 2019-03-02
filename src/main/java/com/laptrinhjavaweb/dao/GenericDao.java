package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.orm.criteria.criterion.Restriction;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface GenericDao<T, ID> {
    List<T> findAll();

    List<T> findByProperties(Pageable pageable, List<Restriction> restrictionList);

    T findOneById(ID id);
}
