package com.adi.corkproject.service;

import com.adi.corkproject.model.UserGroup;
import com.adi.corkproject.repository.UserGroupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService implements  IUserGroupService{

    private final UserGroupRepository userGroupRepository;
    private final UserService userService;

    public UserGroupService(UserGroupRepository userGroupRepository, UserService userService) {
        this.userGroupRepository = userGroupRepository;
        this.userService = userService;
    }

    @Override
    public UserGroup save(UserGroup.GROUP_TYPE group_type) {
        return userGroupRepository.save(new UserGroup(group_type));
    }

}
