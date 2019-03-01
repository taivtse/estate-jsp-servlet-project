package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.BuildingDao;
import com.laptrinhjavaweb.entity.BuildingEntity;

import javax.inject.Named;

@Named("buildingDaoImpl")
public class BuildingDaoImpl extends AbstractDao<BuildingEntity, Integer> implements BuildingDao {
}
