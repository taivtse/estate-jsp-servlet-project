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
import java.util.Date;
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
            buildingDto.setRentalAreaArray(rentAreaNumberList.toArray(new Integer[rentAreaNumberList.size()]));
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
        if (buildingEntity.getType() != null) {
            buildingDto.setTypeArray(buildingEntity.getType().split(","));
        }

//        get rent area and set to rent area array in building dto
        List<RentAreaDto> rentAreaDtoList = rentAreaService.findAllByBuildingId(id);
        List<Integer> rentAreaNumberList = new ArrayList<>();
        rentAreaDtoList.forEach(rentAreaDto -> rentAreaNumberList.add(rentAreaDto.getArea()));
        buildingDto.setRentalAreaArray(rentAreaNumberList.toArray(new Integer[rentAreaNumberList.size()]));

        return buildingDto;
    }

    @Override
    public BuildingDto save(BuildingDto dto) throws Exception {
        dto.setCreatedDate(new Date());
//        TODO: change crated by if authenticated
        dto.setCreatedBy("thanhtai");

        BuildingEntity entity = converter.dtoToEntity(dto);

        if (dto.getTypeArray() != null) {
            entity.setType(String.join(",", dto.getTypeArray()));
        }
        genericDao.save(entity);

        return converter.entityToDto(entity);
    }

    @Override
    public BuildingDto update(BuildingDto dto) throws Exception {
        BuildingEntity oldEntity = genericDao.findOneById(dto.getId());

        dto.setCreatedDate(oldEntity.getCreatedDate());
        dto.setCreatedBy(oldEntity.getCreatedBy());
        dto.setModifiedDate(new Date());
//        TODO: change crated by if authenticated
        dto.setModifiedBy("haimy");

//        TODO: remove all old rent area before update all new

        BuildingEntity entity = converter.dtoToEntity(dto);
        if (dto.getTypeArray() != null) {
            entity.setType(String.join(",", dto.getTypeArray()));
        }
        genericDao.update(entity);

        return converter.entityToDto(entity);
    }

    @Override
    public void deleteById(Integer integer) throws Exception {
        super.deleteById(integer);
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
