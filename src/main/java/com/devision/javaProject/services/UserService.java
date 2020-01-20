package com.devision.javaProject.services;

import com.devision.javaProject.factories.PageFactory;
import com.devision.javaProject.models.Group;
import com.devision.javaProject.models.User;
import com.devision.javaProject.models.dto.UserDTO;
import com.devision.javaProject.models.Plot;
import com.devision.javaProject.repositories.GroupRepository;
import com.devision.javaProject.repositories.PlotRepository;
import com.devision.javaProject.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PageFactory pageFactory;

    public void save(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new Exception( "User can't be created");
        }

    }

    public void addGroup(Long id, String groupName) throws Exception {
        Group group = groupRepository.findByName(groupName);

        if(group == null){
            throw new Exception(String.format("\"%s\" group doesn't exist", groupName.toUpperCase()));
        }

        User user = userRepository.getOne(id);

        if(user == null){
            throw new Exception(String.format("User with id: \"%s\" doesn't exist", id));
        }

        if(user.getGroupList().contains(group)){
            throw new Exception(String.format("this user have been already assigned to \"%s\" group ", group.getName()));
        }

        user.getGroupList().add(group);

        userRepository.saveAndFlush(user);

    }

    public Page<User> getAllUsersByAge(int age, Integer page, Integer size, String sort){

        PageRequest pageRequest = pageFactory.create(page, size, sort);

        return userRepository.findAllByAge(age, pageRequest);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users){
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO, "groupList");

            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    public UserDTO getOne(Long id) throws Exception {
        User user = userRepository.getOne(id);
        if(user == null){
            throw new Exception(String.format("User with id: \"%s\" doesn't exist", id));
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public void addPlot(Long id, Long plotId) throws Exception {

        Plot plot = plotRepository.getOne(plotId);

        if(plot == null){
            throw new Exception(String.format("Plot with id: \"%s\" doesn't exist", plotId));
        }

        User user = userRepository.getOne(id);

        if(user == null){
            throw new Exception(String.format("User with id: \"%s\" doesn't exist", id));
        }

        if(user.getPlotList().contains(plot)){
            throw new Exception(String.format("this user already had \"%s\" plot ", plot.getName()));
        }

        user.getPlotList().add(plot);

        userRepository.saveAndFlush(user);
    }
}
