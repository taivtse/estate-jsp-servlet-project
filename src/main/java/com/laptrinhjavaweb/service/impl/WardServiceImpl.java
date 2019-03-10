package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.WardConverter;
import com.laptrinhjavaweb.dao.impl.WardDaoImpl;
import com.laptrinhjavaweb.dto.WardDto;
import com.laptrinhjavaweb.entity.WardEntity;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Logical;
import com.laptrinhjavaweb.service.WardService;

import java.util.Arrays;
import java.util.List;

public class WardServiceImpl extends AbstractService<Integer, WardDto, WardEntity> implements WardService {
    public WardServiceImpl() {
        super.genericDao = new WardDaoImpl();
        super.converter = new WardConverter();
    }

    @Override
    public List<WardDto> findAllByDistrictId(Integer districtId) {
        Criterion criterion = Logical.and("districtId").eq(districtId);
        return this.findAllByProperties(null, Arrays.asList(criterion));
    }
}
