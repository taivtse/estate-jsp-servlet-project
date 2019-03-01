package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.converter.GenericConverter;
import com.laptrinhjavaweb.dao.GenericDao;

public class GenericServiceImpl<ID, T> implements GenericService<ID, T> {
    private GenericConverter converter;
    private GenericDao<ID, Object> genericDao;
}
