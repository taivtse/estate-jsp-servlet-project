package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDto;
import com.laptrinhjavaweb.entity.UserEntity;

import javax.inject.Named;

@Named("userConverter")
public class UserConverter extends AbstractConverter<UserDto, UserEntity> {
}
