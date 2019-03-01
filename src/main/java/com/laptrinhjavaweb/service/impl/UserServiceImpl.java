package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.AbstractConverter;
import com.laptrinhjavaweb.dao.GenericDao;
import com.laptrinhjavaweb.dto.UserDto;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;

public class UserServiceImpl extends AbstractService<Integer, UserDto, UserEntity> implements UserService {

    @Inject
    public UserServiceImpl(@Named("userDaoImpl") GenericDao genericDao,
                           @Named("userConverter") AbstractConverter abstractConverter) {
        super(genericDao, abstractConverter);
    }
}
