package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.command.BuildingCommand;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.util.FormUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/building/*")
public class BuildingController extends HttpServlet {
    private final String prefixRootPath = "/views/admin/building/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getPathInfo();
        if (type != null) {
            type = type.substring(1);
        }

        BuildingCommand command = FormUtil.populate(BuildingCommand.class, req);

        if (type.startsWith(SystemConstant.TYPE_LIST)) {
            req.setAttribute(SystemConstant.COMMAND, command);
            req.getRequestDispatcher(prefixRootPath.concat("list.jsp")).forward(req, resp);
        } else if (type.startsWith(SystemConstant.TYPE_EDIT)) {
            req.getRequestDispatcher(prefixRootPath.concat("edit.jsp")).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
