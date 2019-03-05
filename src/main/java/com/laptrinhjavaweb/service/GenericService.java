package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface GenericService<ID, D> {
    List<D> findAll();

    List<D> findAllByProperties(Pageable pageable, List<Criterion> criterionList);

    D findOneById(ID id);

    D save(D dto) throws Exception;

    D update(D dto) throws Exception;

    void delete(D dto) throws Exception;

    void deleteById(ID id) throws Exception;
}
