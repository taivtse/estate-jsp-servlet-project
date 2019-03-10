package com.laptrinhjavaweb.command;

import com.laptrinhjavaweb.dto.BuildingDto;
import com.laptrinhjavaweb.dto.DistrictDto;
import com.laptrinhjavaweb.dto.WardDto;

import java.util.List;

public class BuildingCommand extends AbstractCommand<BuildingDto> {
    private List<DistrictDto> districtDtoList;
    private List<WardDto> wardDtoList;
    private BuildingType[] buildingTypeArray = BuildingType.values();

    public BuildingCommand() {
        this.pojo = new BuildingDto();
    }

    public List<DistrictDto> getDistrictDtoList() {
        return districtDtoList;
    }

    public void setDistrictDtoList(List<DistrictDto> districtDtoList) {
        this.districtDtoList = districtDtoList;
    }

    public List<WardDto> getWardDtoList() {
        return wardDtoList;
    }

    public void setWardDtoList(List<WardDto> wardDtoList) {
        this.wardDtoList = wardDtoList;
    }

    public BuildingType[] getBuildingTypeArray() {
        return buildingTypeArray;
    }

    public void setBuildingTypeArray(BuildingType[] buildingTypeArray) {
        this.buildingTypeArray = buildingTypeArray;
    }

    public enum BuildingType {
        GROUND_FLOOR("Tầng trệt"),
        APARTMENT("Nguyên căn"),
        FURNITURE("Nội thất");

        private String typeName;

        BuildingType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}
