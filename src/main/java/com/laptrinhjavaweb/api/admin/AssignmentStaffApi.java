package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.command.AssignmentCommand;
import com.laptrinhjavaweb.dto.AssignmentDto;
import com.laptrinhjavaweb.dto.StaffAssignmentDto;
import com.laptrinhjavaweb.service.AssignmentService;
import com.laptrinhjavaweb.service.UserService;
import com.laptrinhjavaweb.service.impl.AssignmentServiceImpl;
import com.laptrinhjavaweb.service.impl.UserServiceImpl;
import com.laptrinhjavaweb.util.HttpUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/assignment/staff")
public class AssignmentStaffApi extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private AssignmentService assignmentService = new AssignmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer buildingId = Integer.parseInt(req.getParameter("buildingId"));
        List<StaffAssignmentDto> staffAssignmentDtoList = userService.getStaffAssignmentListByBuildingId(buildingId);
        HttpUtil.writeValue(resp.getOutputStream(), staffAssignmentDtoList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AssignmentCommand command = HttpUtil.of(req.getReader()).toObject(AssignmentCommand.class);

        if (command.getCheckList() != null && command.getBuildingId() != null) {
            try {
                assignmentService.deleteAllByBuildingId(command.getBuildingId());
                for (String staffStrId : command.getCheckList()) {
                    Integer staffId = Integer.parseInt(staffStrId);

                    AssignmentDto assignmentDto = new AssignmentDto();
                    assignmentDto.setUserId(staffId);
                    assignmentDto.setBuildingId(command.getBuildingId());

                    assignmentService.save(assignmentDto);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    }
}