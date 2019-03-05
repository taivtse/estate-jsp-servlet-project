package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.UserDto;
import com.laptrinhjavaweb.service.UserService;
import com.laptrinhjavaweb.util.HttpUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/user")
public class UserApi extends HttpServlet {

//    @Inject
//    @Named("userServiceImpl")
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<UserDto> userDtoList = userService.findAll();
        HttpUtil.writeValue(resp.getOutputStream(), userDtoList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    }
}
