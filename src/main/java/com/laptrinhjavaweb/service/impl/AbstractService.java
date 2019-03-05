package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.GenericConverter;
import com.laptrinhjavaweb.dao.GenericDao;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.GenericService;

import java.util.ArrayList;
import java.util.List;

public class AbstractService<ID, D, E> implements GenericService<ID, D> {
    protected GenericConverter<D, E> converter;
    protected GenericDao<E, ID> genericDao;

    @Override
    public List<D> findAll() {
        List<E> entityList = genericDao.findAll();
        List<D> dtoList = new ArrayList<>();

//        convert entity to dto and add it to dto list
        for (E entity : entityList) {
            dtoList.add(converter.entityToDto(entity));
        }

        return dtoList;
    }

    @Override
    public List<D> findAllByProperties(Pageable pageable, List<Criterion> criterionList) {
        List<E> entityList = genericDao.findAllByProperties(pageable, criterionList);
        List<D> dtoList = new ArrayList<>();

//        convert entity to dto and add it to dto list
        for (E entity : entityList) {
            dtoList.add(converter.entityToDto(entity));
        }

        return dtoList;
    }

    @Override
    public D findOneById(ID id) {
        E entity = genericDao.findOneById(id);
        return converter.entityToDto(entity);
    }

    @Override
    public D save(D dto) throws Exception {
        E entity = converter.dtoToEntity(dto);
        entity = genericDao.save(entity);
        return converter.entityToDto(entity);
    }

    @Override
    public D update(D dto) throws Exception {
        E entity = converter.dtoToEntity(dto);
        entity = genericDao.update(entity);
        return converter.entityToDto(entity);
    }

    @Override
    public void delete(D dto) throws Exception {
        E entity = converter.dtoToEntity(dto);
        genericDao.delete(entity);
    }

    @Override
    public void deleteById(ID id) throws Exception {
        genericDao.deleteById(id);
    }
}
