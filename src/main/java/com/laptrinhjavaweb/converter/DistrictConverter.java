package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.DistrictDto;
import com.laptrinhjavaweb.entity.DistrictEntity;

import javax.inject.Named;

@Named("districtConverter")
public class DistrictConverter extends AbstractConverter<DistrictDto, DistrictEntity> {
}
