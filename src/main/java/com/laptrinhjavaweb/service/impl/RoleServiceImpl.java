package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.RoleConverter;
import com.laptrinhjavaweb.dao.impl.RoleDaoImpl;
import com.laptrinhjavaweb.dto.RoleDto;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.service.RoleService;

public class RoleServiceImpl extends AbstractService<Integer, RoleDto, RoleEntity> implements RoleService {
    public RoleServiceImpl() {
        super.genericDao = new RoleDaoImpl();
        super.converter = new RoleConverter();
    }
}
