package com.laptrinhjavaweb.model;

import com.laptrinhjavaweb.core.anotation.Column;
import com.laptrinhjavaweb.core.anotation.Entity;
import com.laptrinhjavaweb.core.anotation.Id;

@Entity(tableName = "assignment")
public class AssignmentModel {
    @Id(autoIncrement = true)
    @Column(name = "id")
    private Integer id;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
