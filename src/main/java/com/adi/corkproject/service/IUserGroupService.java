package com.adi.corkproject.service;


import com.adi.corkproject.model.UserGroup;
import org.springframework.context.annotation.Bean;

public interface IUserGroupService {
    UserGroup save(UserGroup.GROUP_TYPE group_type);

}
