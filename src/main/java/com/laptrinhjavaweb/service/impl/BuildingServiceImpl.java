package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dao.impl.BuildingDaoImpl;
import com.laptrinhjavaweb.dto.BuildingDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.service.BuildingService;

import java.util.ArrayList;
import java.util.List;

public class BuildingServiceImpl extends AbstractService<Integer, BuildingDto, BuildingEntity> implements BuildingService {
    public BuildingServiceImpl() {
        super.genericDao = new BuildingDaoImpl();
        super.converter = new BuildingConverter();
    }

    @Override
    public List<BuildingDto> findAll() {
        List<BuildingEntity> entityList = genericDao.findAll();
        List<BuildingDto> dtoList = new ArrayList<>();

//        convert entity to dto and add it to dto list
        for (BuildingEntity entity : entityList) {
            BuildingDto dto = converter.entityToDto(entity);
            dto.setFullAddress(this.buildAddress(dto.getDistrict(), dto.getWard(), dto.getStreet()));
            dtoList.add(dto);
        }

        return dtoList;
    }

    private String buildAddress(String district, String ward, String street) {
        String address = street;

        if (ward != null && !ward.isEmpty()) {
            address = address.concat(", ").concat(ward);
        }

        if (district != null && !district.isEmpty()) {
            address = address.concat(", ").concat(district);
        }

        return address;
    }
}
