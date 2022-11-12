package com.adi.corkproject.controller;

import com.adi.corkproject.exception.UserExistsException;
import com.adi.corkproject.model.User;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public @ResponseBody List<User> getAllUsers(){
        init();
        return userService.findAllUsers();
    }

    public void init(){
        try {
            userService.save("adrianoitaliano@gmail.com", "adriaaan7", "password");
            userService.save("medusaofficial@gmail.com", "medusaofficial", "medusapass");
        }catch (UserExistsException exception){
            exception.printStackTrace();
        }
    }
}
