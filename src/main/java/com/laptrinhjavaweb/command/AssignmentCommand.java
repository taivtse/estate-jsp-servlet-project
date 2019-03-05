package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.AssignmentDto;

public class AssignmentCommand extends AbstractCommand<AssignmentDto> {
    public AssignmentCommand() {
        this.pojo = new AssignmentDto();
    }
}
