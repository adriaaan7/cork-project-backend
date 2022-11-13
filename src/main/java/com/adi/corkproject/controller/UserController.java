package com.adi.corkproject.controller;

import com.adi.corkproject.config.UserGroupConfiguration;
import com.adi.corkproject.exception.UserNotExistingException;
import com.adi.corkproject.model.User;
import com.adi.corkproject.model.UserGroup;
import com.adi.corkproject.service.UserGroupService;
import com.adi.corkproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserGroupService userGroupService;
    private final UserGroupConfiguration userGroupConfiguration;
    
    public UserController(UserService userService, UserGroupService userGroupService, UserGroupConfiguration userGroupConfiguration) {
        this.userService = userService;
        this.userGroupService = userGroupService;
        this.userGroupConfiguration = userGroupConfiguration;
    }

    @PostMapping("/add")
    public User addUser(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String group
    ){
        log.info("Adding user to the database");
        UserGroup.GROUP_TYPE groupType = group.equalsIgnoreCase("student") ? UserGroup.GROUP_TYPE.STUDENT : UserGroup.GROUP_TYPE.TEACHER;
        UserGroup userGroup;
        if(groupType == UserGroup.GROUP_TYPE.STUDENT){
            userGroup = userGroupConfiguration.studentGroup();
        } else {
            userGroup = userGroupConfiguration.teacherGroup();
        }
        return userService.save(email, username, password, userGroup);
    }

    @DeleteMapping("/remove")
    public User deleteUserById(@RequestParam Long id){
        log.info("Deleting user from the database with id: " + id);
        User user = userService.deleteUserById(id);
        try {
            return user;
        }catch (UserNotExistingException exception){
            exception.printStackTrace();
        }
        return user;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }
}
