package com.adi.corkproject.service;

import com.adi.corkproject.model.User;
import com.adi.corkproject.model.UserGroup;

import java.util.List;

public interface IUserService {
    List<User> findAllUsers();
    User findUserById(Long id);
    User save(String email, String username, String password, UserGroup userGroup);

    boolean isPresent(String email, String username);

    boolean checkIfUserIsPresentByEmail(String email);
    boolean checkIfUserIsPresentByUsername(String username);
}
