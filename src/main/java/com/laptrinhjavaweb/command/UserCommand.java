package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.UserDto;

public class UserCommand extends AbstractCommand<UserDto> {
    private UserCommand(UserDto pojo) {
        super(pojo);
    }
}
