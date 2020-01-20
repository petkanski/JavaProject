package com.devision.javaProject.models.projections;

import com.devision.javaProject.models.Group;
import com.devision.javaProject.models.User;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "userDetailView", types = {User.class})
public interface UserDetailView {

    Long getUser_id();
    String getName();
    String getEmail();
    Integer getAge();
    List<Group> getGroupList();
}
