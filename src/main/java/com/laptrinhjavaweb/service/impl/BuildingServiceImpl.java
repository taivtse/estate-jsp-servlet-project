package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dao.impl.BuildingDaoImpl;
import com.laptrinhjavaweb.dto.BuildingDto;
import com.laptrinhjavaweb.dto.DistrictDto;
import com.laptrinhjavaweb.dto.WardDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.DistrictService;
import com.laptrinhjavaweb.service.WardService;

import java.util.ArrayList;
import java.util.List;

public class BuildingServiceImpl extends AbstractService<Integer, BuildingDto, BuildingEntity> implements BuildingService {
    private DistrictService districtService = new DistrictServiceImpl();
    private WardService wardService = new WardServiceImpl();

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

//            get district and ward name to concatenate to full address
            WardDto wardDto = wardService.findOneById(dto.getWardId());
            DistrictDto districtDto = districtService.findOneById(wardDto.getDistrictId());

            dto.setFullAddress(this.buildAddress(districtDto.getName(), wardDto.getName(), dto.getStreet()));
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public BuildingDto findOneById(Integer id) {
        BuildingDto dto =  super.findOneById(id);

//            get district and ward name to concatenate to full address
        WardDto wardDto = wardService.findOneById(dto.getWardId());
        DistrictDto districtDto = districtService.findOneById(wardDto.getDistrictId());

        dto.setFullAddress(this.buildAddress(districtDto.getName(), wardDto.getName(), dto.getStreet()));

        return dto;
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
