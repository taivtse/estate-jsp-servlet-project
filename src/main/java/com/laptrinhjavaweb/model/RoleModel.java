package com.laptrinhjavaweb.model;

import com.laptrinhjavaweb.orm.anotation.Column;
import com.laptrinhjavaweb.orm.anotation.Entity;
import com.laptrinhjavaweb.orm.anotation.Id;

@Entity(tableName = "role")
public class RoleModel {
    @Id(autoIncrement = false)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

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
}
