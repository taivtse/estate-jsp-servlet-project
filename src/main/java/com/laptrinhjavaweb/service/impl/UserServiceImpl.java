package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dao.UserDao;
import com.laptrinhjavaweb.dao.impl.UserDaoImpl;
import com.laptrinhjavaweb.dto.StaffAssignmentDto;
import com.laptrinhjavaweb.dto.UserDto;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.AssignmentService;
import com.laptrinhjavaweb.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends AbstractService<Integer, UserDto, UserEntity> implements UserService {
    private AssignmentService assignmentService = new AssignmentServiceImpl();

    public UserServiceImpl() {
        super.genericDao = new UserDaoImpl();
        super.converter = new UserConverter();
    }

    @Override
    public List<StaffAssignmentDto> getStaffAssignmentListByBuildingId(Integer buildingId) {
        List<UserEntity> userEntityList = ((UserDao) genericDao).findAllActiveStaffList();
        List<StaffAssignmentDto> staffAssignmentDtoList = new ArrayList<>();

        userEntityList.forEach(staff -> {
            StaffAssignmentDto staffAssignmentDto = new StaffAssignmentDto();
            staffAssignmentDto.setId(staff.getId());
            staffAssignmentDto.setFullName(staff.getFullName());

            if (assignmentService.isExistByStaffIdAndBuildingId(staff.getId(), buildingId)) {
                staffAssignmentDto.setChecked("checked");
            } else {
                staffAssignmentDto.setChecked("");
            }

            staffAssignmentDtoList.add(staffAssignmentDto);
        });

        return staffAssignmentDtoList;
    }
}
