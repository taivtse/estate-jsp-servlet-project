package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.AssignmentDto;

public class AssignmentCommand extends AbstractCommand<AssignmentDto> {
    private Integer buildingId;

    public AssignmentCommand() {
        this.pojo = new AssignmentDto();
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }
}
