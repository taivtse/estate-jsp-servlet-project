package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.DistrictDto;

public class DistrictCommand extends AbstractCommand<DistrictDto> {
    private DistrictCommand() {
        this.pojo = new DistrictDto();
    }
}
