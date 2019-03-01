package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.AssignmentDao;
import com.laptrinhjavaweb.entity.AssignmentEntity;

import javax.inject.Named;

@Named("assignmentDaoImpl")
public class AssignmentDaoImpl extends AbstractDao<AssignmentEntity, Integer> implements AssignmentDao {
}
