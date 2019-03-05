package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dao.impl.UserDaoImpl;
import com.laptrinhjavaweb.dto.UserDto;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.UserService;

public class UserServiceImpl extends AbstractService<Integer, UserDto, UserEntity> implements UserService {
    public UserServiceImpl() {
        super.genericDao = new UserDaoImpl();
        super.converter = new UserConverter();
    }
}
