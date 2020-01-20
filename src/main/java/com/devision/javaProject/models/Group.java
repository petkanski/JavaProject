package com.devision.javaProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "groups")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "groupList")
    @JsonIgnore
    private Set<User> userList;


    public Group() {
    }

    public Group(String group) {
        this.setName(group);
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
