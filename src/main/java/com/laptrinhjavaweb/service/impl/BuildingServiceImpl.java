package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dao.impl.BuildingDaoImpl;
import com.laptrinhjavaweb.dto.BuildingDto;
import com.laptrinhjavaweb.dto.DistrictDto;
import com.laptrinhjavaweb.dto.RentAreaDto;
import com.laptrinhjavaweb.dto.WardDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.DistrictService;
import com.laptrinhjavaweb.service.RentAreaService;
import com.laptrinhjavaweb.service.WardService;

import java.util.ArrayList;
import java.util.List;

public class BuildingServiceImpl extends AbstractService<Integer, BuildingDto, BuildingEntity> implements BuildingService {
    private DistrictService districtService = new DistrictServiceImpl();
    private WardService wardService = new WardServiceImpl();
    private RentAreaService rentAreaService = new RentAreaServiceImpl();

    public BuildingServiceImpl() {
        super.genericDao = new BuildingDaoImpl();
        super.converter = new BuildingConverter();
    }

    @Override
    public List<BuildingDto> findAll() {
        List<BuildingDto> buildingDtoList = super.findAll();

//        convert entity to dto and add it to dto list
        for (BuildingDto buildingDto : buildingDtoList) {

//            get district and ward name to concatenate to full address
            WardDto wardDto = wardService.findOneById(buildingDto.getWardId());
            DistrictDto districtDto = districtService.findOneById(wardDto.getDistrictId());
            buildingDto.setFullAddress(this.buildAddress(districtDto.getName(), wardDto.getName(), buildingDto.getStreet()));

//        get rent area and set to rent area array in building dto
            List<RentAreaDto> rentAreaDtoList = rentAreaService.findAllByBuildingId(buildingDto.getId());
            List<Integer> rentAreaNumberList = new ArrayList<>();
            rentAreaDtoList.forEach(rentAreaDto -> rentAreaNumberList.add(rentAreaDto.getArea()));
            buildingDto.setRentalAreaArr(rentAreaNumberList.toArray(new Integer[rentAreaNumberList.size()]));
        }

        return buildingDtoList;
    }

    @Override
    public BuildingDto findOneById(Integer id) {
        BuildingEntity buildingEntity = super.genericDao.findOneById(id);
        BuildingDto buildingDto = super.converter.entityToDto(buildingEntity);

//            get district and ward name to concatenate to full address and set district id
        WardDto wardDto = wardService.findOneById(buildingDto.getWardId());
        DistrictDto districtDto = districtService.findOneById(wardDto.getDistrictId());

        buildingDto.setDistrictId(districtDto.getId());
        buildingDto.setFullAddress(this.buildAddress(districtDto.getName(), wardDto.getName(), buildingDto.getStreet()));

//        split structure to array
        buildingDto.setTypeArr(buildingEntity.getType().split(","));

//        get rent area and set to rent area array in building dto
        List<RentAreaDto> rentAreaDtoList = rentAreaService.findAllByBuildingId(id);
        List<Integer> rentAreaNumberList = new ArrayList<>();
        rentAreaDtoList.forEach(rentAreaDto -> rentAreaNumberList.add(rentAreaDto.getArea()));
        buildingDto.setRentalAreaArr(rentAreaNumberList.toArray(new Integer[rentAreaNumberList.size()]));

        return buildingDto;
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
