package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.AssignmentDto;

public class AssignmentCommand extends AbstractCommand<AssignmentDto> {
    private AssignmentCommand() {
        this.pojo = new AssignmentDto();
    }
}
