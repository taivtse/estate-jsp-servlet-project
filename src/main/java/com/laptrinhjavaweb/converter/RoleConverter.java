package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.RoleDto;
import com.laptrinhjavaweb.entity.RoleEntity;

import javax.inject.Named;

@Named("roleConverter")
public class RoleConverter extends AbstractConverter<RoleDto, RoleEntity> {
}
