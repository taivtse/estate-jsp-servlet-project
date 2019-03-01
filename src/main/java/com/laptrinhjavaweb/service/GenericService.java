package com.laptrinhjavaweb.service;

import java.util.List;

public interface GenericService<ID, D, E> {
    List<D> findAll();

//    List<D> findAllByProperties(Pageable pageable, List<SearchProperty> properties);

    D findOneById(ID id);

//    D save(D dto) throws Exception;
//
//    D update(D dto) throws Exception;
//
//    void delete(D dto) throws Exception;
//
//    void deleteById(ID id) throws Exception;
}
