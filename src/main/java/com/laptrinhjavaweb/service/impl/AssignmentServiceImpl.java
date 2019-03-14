package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.AssignmentConverter;
import com.laptrinhjavaweb.dao.AssignmentDao;
import com.laptrinhjavaweb.dao.impl.AssignmentDaoImpl;
import com.laptrinhjavaweb.dto.AssignmentDto;
import com.laptrinhjavaweb.entity.AssignmentEntity;
import com.laptrinhjavaweb.service.AssignmentService;

public class AssignmentServiceImpl extends AbstractService<Integer, AssignmentDto, AssignmentEntity> implements AssignmentService {
    public AssignmentServiceImpl() {
        super.genericDao = new AssignmentDaoImpl();
        super.converter = new AssignmentConverter();
    }

    @Override
    public void deleteAllByBuildingId(Integer id) throws Exception {
        ((AssignmentDaoImpl) genericDao).deleteAllByBuildingId(id);
    }

    @Override
    public boolean isExistByStaffIdAndBuildingId(Integer staffId, Integer buildingId) {
        AssignmentEntity assignmentEntity = ((AssignmentDao) genericDao).findOneByStaffIdAndBuildingId(staffId, buildingId);
        return assignmentEntity != null;
    }
}
