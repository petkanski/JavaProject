package com.devision.javaProject.controllers;

import com.devision.javaProject.models.User;
import com.devision.javaProject.models.dto.UserDTO;
import com.devision.javaProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void create(@RequestBody User user) throws Exception {
        userService.save(user);
    }

    @RequestMapping(value = "/{id}/addGroup", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addGroup(@PathVariable Long id, @RequestParam String groupName) throws Exception {
        userService.addGroup(id, groupName);
    }

    @GetMapping("/age")
    public Page<User> getAllUsersByAge(@RequestParam int age,
                                       @RequestParam(required = false) Integer pageNum,
                                       @RequestParam(required = false) Integer size,
                                       @RequestParam(required = false) String sortBy){

        return userService.getAllUsersByAge(age, pageNum, size, sortBy);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping
    public List<UserDTO> findAll(){
       return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findOne(@PathVariable Long id) throws Exception {
       return userService.getOne(id);
    }

    @RequestMapping(value = "/{id}/addPlot", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addPlot(@PathVariable Long id, @RequestParam Long plotId) throws Exception {
        userService.addPlot(id, plotId);
    }
}
