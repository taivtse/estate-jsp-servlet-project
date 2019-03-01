package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.RoleDao;
import com.laptrinhjavaweb.entity.RoleEntity;

import javax.inject.Named;

@Named("roleDaoImpl")
public class RoleDaoImpl extends AbstractDao<RoleEntity, String> implements RoleDao {
}
