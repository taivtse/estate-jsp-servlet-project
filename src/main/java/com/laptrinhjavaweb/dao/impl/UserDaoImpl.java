package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.UserDao;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Logical;

import java.util.Arrays;
import java.util.List;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    @Override
    public List<UserEntity> findAllActiveStaffList() {
        Criterion criterion = Logical.and("roleId").eq(2);
        Criterion criterion1 = Logical.and("status").eq(true);
        return this.findAllByProperties(null, Arrays.asList(criterion, criterion1));
    }
}
