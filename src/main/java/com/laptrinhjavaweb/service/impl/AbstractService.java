package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.AbstractConverter;
import com.laptrinhjavaweb.converter.GenericConverter;
import com.laptrinhjavaweb.dao.GenericDao;
import com.laptrinhjavaweb.service.GenericService;

import java.util.ArrayList;
import java.util.List;

public class AbstractService<ID, D, E> implements GenericService<ID, D, E> {
    private GenericConverter<D, E> converter;
    private GenericDao<ID, Object> genericDao;

    public AbstractService(GenericDao genericDao, AbstractConverter abstractConverter) {
        this.converter = abstractConverter;
        this.genericDao = genericDao;
    }

    @Override
    public List<D> findAll() {
        List<E> entityList = (List<E>) genericDao.findAll();
        List<D> dtoList = null;

        if (entityList != null) {
            dtoList = new ArrayList<>();
        }

//        convert entity to dto and add it to dto list
        for (E entity : entityList) {
            dtoList.add(converter.entityToDto(entity));
        }
        return dtoList;
    }

    @Override
    public D findOneById(ID id) {
        E entity = (E) genericDao.findOneById(id);
        return converter.entityToDto(entity);
    }
}
