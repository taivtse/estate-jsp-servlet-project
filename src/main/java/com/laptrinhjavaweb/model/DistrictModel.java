package com.laptrinhjavaweb.model;

import com.laptrinhjavaweb.orm.anotation.Column;
import com.laptrinhjavaweb.orm.anotation.Entity;
import com.laptrinhjavaweb.orm.anotation.Id;

@Entity(tableName = "district")
public class DistrictModel {
    @Id(autoIncrement = true)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "province_id")
    private String provinceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
