package com.adi.corkproject.controller;

import com.adi.corkproject.exception.UserExistsException;
import com.adi.corkproject.model.User;
import com.adi.corkproject.model.UserGroup;
import com.adi.corkproject.service.UserGroupService;
import com.adi.corkproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserGroupService userGroupService;
    public UserController(UserService userService, UserGroupService userGroupService) {
        this.userService = userService;
        this.userGroupService = userGroupService;
    }

    @GetMapping("/all")
    public @ResponseBody List<User> getAllUsers(){
        return userService.findAllUsers();
    }
}
