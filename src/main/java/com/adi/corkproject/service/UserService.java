package com.adi.corkproject.service;

import com.adi.corkproject.exception.UserExistingException;
import com.adi.corkproject.exception.UserNotExistingException;
import com.adi.corkproject.exception.UserNotFoundException;
import com.adi.corkproject.model.User;
import com.adi.corkproject.model.UserGroup;
import com.adi.corkproject.repository.UserGroupRepository;
import com.adi.corkproject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    public UserService(UserRepository repository, UserGroupRepository userGroupRepository) {
        this.userRepository = repository;
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public List<User> findAllUsers() {
        log.info("Fetching all users from database");
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        log.info("Searching for user with id: " + id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            throw new UserNotFoundException("User with id: " + id + " was not found");

        return optionalUser.get();
    }

    /*
        #TODO
        Checking if email is valid
        Later on: hashing password and changing how it is stored
     */
    @Override
    public User save(String email, String username, String password, UserGroup userGroup) {
        if(isPresent(email, username))
            throw new UserExistingException("User with email: " + email + " and username: " + username + " already exists in the database");
        log.info("Saving user to the database");
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);
        System.out.println(userGroup);
        User user = new User(email, username, password, userGroup);
        userGroup.getUser().add(user);
        System.out.println(userRepository.save(user));
        return user;
    }

    @Override
    public boolean isPresent(String email, String username) {
        return checkIfUserIsPresentByEmail(email) || checkIfUserIsPresentByUsername(username);
    }

    @Override
    public boolean checkIfUserIsPresentByEmail(String email) {
        var users = userRepository.findAll();
        if(users.isEmpty()) return false;
        return users
                .stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public boolean checkIfUserIsPresentByUsername(String username) {
        var users = userRepository.findAll();
        return users
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public User deleteUserById(Long id) {
        User user = new User();
        if (userRepository.findById(id).isPresent()){
            user = userRepository.findById(id).get();
            userRepository.delete(user);
        }
        if(user.getId() <= 0) throw new UserNotExistingException("User with id: " + id + "does not exist in the database");
        return user;
    }
}
