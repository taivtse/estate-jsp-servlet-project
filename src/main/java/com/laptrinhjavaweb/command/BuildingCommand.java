package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.BuildingDto;

public class BuildingCommand extends AbstractCommand<BuildingDto> {
    private BuildingCommand() {
        this.pojo = new BuildingDto();
    }
}
