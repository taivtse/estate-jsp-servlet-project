package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.AssignmentDto;
import com.laptrinhjavaweb.entity.AssignmentEntity;

import javax.inject.Named;

@Named("assignmentConverter")
public class AssignmentConverter extends AbstractConverter<AssignmentDto, AssignmentEntity> {
}
