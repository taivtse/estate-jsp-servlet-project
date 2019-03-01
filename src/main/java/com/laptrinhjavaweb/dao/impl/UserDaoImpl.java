package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.UserDao;
import com.laptrinhjavaweb.entity.UserEntity;

import javax.inject.Named;

@Named("userDaoImpl")
public class UserDaoImpl extends AbstractDao<UserEntity, Integer> implements UserDao {
}
