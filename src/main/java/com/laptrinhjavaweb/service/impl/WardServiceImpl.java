package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.WardConverter;
import com.laptrinhjavaweb.dao.impl.WardDaoImpl;
import com.laptrinhjavaweb.dto.WardDto;
import com.laptrinhjavaweb.entity.WardEntity;
import com.laptrinhjavaweb.service.WardService;

public class WardServiceImpl extends AbstractService<Integer, WardDto, WardEntity> implements WardService {
    public WardServiceImpl() {
        super.genericDao = new WardDaoImpl();
        super.converter = new WardConverter();
    }
}
