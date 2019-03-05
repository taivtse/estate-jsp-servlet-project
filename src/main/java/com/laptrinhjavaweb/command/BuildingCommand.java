package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.BuildingDto;

public class BuildingCommand extends AbstractCommand<BuildingDto> {
    public BuildingCommand() {
        this.pojo = new BuildingDto();
    }
}
