package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.BuildingDto;

public class BuildingCommand extends AbstractCommand<BuildingDto> {
    private BuildingCommand(BuildingDto pojo) {
        super(pojo);
    }
}
