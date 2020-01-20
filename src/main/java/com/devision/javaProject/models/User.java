package com.devision.javaProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotNull
    @Size(min = 1, max = 15)
    private String name;

    @NotNull
    private String email;

    @NotNull
    @Range(min = 18, max = 110)
    private Integer age;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groupList = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Plot> plotList;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User(){}

    public User(String name, String email, Integer age, String password, String username, List<Group> groupList, List<Plot> plotList, boolean active){
        this.setName(name);
        this.setEmail(email);
        this.setAge(age);
        this.setPassword(password);
        this.setUsername(username);
        this.setGroupList(groupList);
        this.setPlotList(plotList);
        this.setActive(active);
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long id) {
        this.user_id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Group> getGroupList() {
        return this.groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList.addAll(groupList);
    }

    public List<Plot> getPlotList() {
        return plotList;
    }

    public void setPlotList(List<Plot> plotList) {
        this.plotList = plotList;
    }
}
