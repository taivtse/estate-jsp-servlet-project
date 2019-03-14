package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.command.BuildingCommand;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.BuildingDto;
import com.laptrinhjavaweb.dto.DistrictDto;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.DistrictService;
import com.laptrinhjavaweb.service.WardService;
import com.laptrinhjavaweb.service.impl.BuildingServiceImpl;
import com.laptrinhjavaweb.service.impl.DistrictServiceImpl;
import com.laptrinhjavaweb.service.impl.WardServiceImpl;
import com.laptrinhjavaweb.util.FormUtil;
import com.laptrinhjavaweb.util.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/building/*")
public class BuildingController extends HttpServlet {
    private final String viewRootPath = "/views/admin/building/";

    private BuildingService buildingService = new BuildingServiceImpl();
    private DistrictService districtService = new DistrictServiceImpl();
    private WardService wardService = new WardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getPathInfo();
        if (type != null) {
            type = type.substring(1);
        }

        try {
            BuildingCommand command = FormUtil.populate(BuildingCommand.class, req);

//            set district list to building command
            List<DistrictDto> districtDtoList = districtService.findAll();
            command.setDistrictDtoList(districtDtoList);

            if (type.startsWith(SystemConstant.TYPE_LIST)) {
                command.setListResult(buildingService.findAll());
//            set ward list
                command.setWardDtoList(wardService.findAllByDistrictId(districtDtoList.get(0).getId()));

                req.setAttribute(SystemConstant.COMMAND, command);
                req.getRequestDispatcher(viewRootPath.concat("list.jsp")).forward(req, resp);

            } else if (type.startsWith(SystemConstant.TYPE_EDIT)) {
                String buildingStrId = type.substring(SystemConstant.TYPE_EDIT.length());

                if (!buildingStrId.isEmpty()) {
                    buildingStrId = buildingStrId.substring(1);
                    Integer buildingId = Integer.parseInt(buildingStrId);

                    BuildingDto buildingDto = buildingService.findOneById(buildingId);
                    command.setPojo(buildingDto);

                    command.setWardDtoList(wardService.findAllByDistrictId(buildingDto.getDistrictId()));
                } else {
                    command.setWardDtoList(wardService.findAllByDistrictId(districtDtoList.get(0).getId()));
                }

                req.setAttribute(SystemConstant.COMMAND, command);
                req.getRequestDispatcher(viewRootPath.concat("edit.jsp")).forward(req, resp);

            } else {
                resp.sendRedirect("/admin/building/list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/admin/building/list");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BuildingDto buildingDto = HttpUtil.of(req.getReader()).toObject(BuildingDto.class);
        try {
            buildingDto = buildingService.save(buildingDto);
            HttpUtil.writeValue(resp.getOutputStream(), buildingDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BuildingDto buildingDto = HttpUtil.of(req.getReader()).toObject(BuildingDto.class);
        try {
            buildingDto = buildingService.update(buildingDto);
            HttpUtil.writeValue(resp.getOutputStream(), buildingDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BuildingCommand command = HttpUtil.of(req.getReader()).toObject(BuildingCommand.class);
        Integer[] intArray = new Integer[command.getCheckList().length];
        int i = 0;
        for (String strId : command.getCheckList()) {
            intArray[i] = Integer.parseInt(strId.trim());
            i++;
        }
        try {
            buildingService.deleteById(intArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
