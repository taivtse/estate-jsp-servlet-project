package com.laptrinhjavaweb.model;

import com.laptrinhjavaweb.core.anotation.Column;
import com.laptrinhjavaweb.core.anotation.Entity;
import com.laptrinhjavaweb.core.anotation.GeneratedValue;
import com.laptrinhjavaweb.core.anotation.Id;

@Entity(tableName = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(autoIncrement = true)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "role_id")
    private String roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
