package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.StaffAssignmentDto;
import com.laptrinhjavaweb.dto.UserDto;

import java.util.List;

public interface UserService extends GenericService<Integer, UserDto> {
    List<StaffAssignmentDto> getStaffAssignmentListByBuildingId(Integer buildingId);
}
