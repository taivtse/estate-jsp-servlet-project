package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.DistrictDao;
import com.laptrinhjavaweb.entity.DistrictEntity;

import javax.inject.Named;

@Named("districtDaoImpl")
public class DistrictDaoImpl extends AbstractDao<DistrictEntity, String> implements DistrictDao {
}
