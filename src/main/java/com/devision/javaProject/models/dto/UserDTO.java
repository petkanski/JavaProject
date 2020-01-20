package com.devision.javaProject.models.dto;

import com.devision.javaProject.models.Group;
import com.devision.javaProject.models.Plot;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Set;

public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Group> groupList;

    private List<Plot> plotList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(Set<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Plot> getPlotList() {
        return plotList;
    }

    public void setPlotList(List<Plot> plotList) {
        this.plotList = plotList;
    }
}
