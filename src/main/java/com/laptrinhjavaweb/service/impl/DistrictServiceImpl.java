package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.DistrictConverter;
import com.laptrinhjavaweb.dao.impl.DistrictDaoImpl;
import com.laptrinhjavaweb.dto.DistrictDto;
import com.laptrinhjavaweb.entity.DistrictEntity;
import com.laptrinhjavaweb.service.DistrictService;

public class DistrictServiceImpl extends AbstractService<String, DistrictDto, DistrictEntity> implements DistrictService {
    public DistrictServiceImpl() {
        super.genericDao = new DistrictDaoImpl();
        super.converter = new DistrictConverter();
    }
}
