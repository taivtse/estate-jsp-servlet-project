package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDto;
import com.laptrinhjavaweb.entity.BuildingEntity;

import javax.inject.Named;

@Named("buildingConverter")
public class BuildingConverter extends AbstractConverter<BuildingDto, BuildingEntity> {
}
