package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dao.impl.BuildingDaoImpl;
import com.laptrinhjavaweb.dto.BuildingDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl extends AbstractService<Integer, BuildingDto, BuildingEntity> implements BuildingService {
    public BuildingServiceImpl() {
        super.genericDao = new BuildingDaoImpl();
        super.converter = new BuildingConverter();
    }
}
