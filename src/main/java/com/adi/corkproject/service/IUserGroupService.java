package com.adi.corkproject.service;


import com.adi.corkproject.model.UserGroup;

public interface IUserGroupService {
    UserGroup save(UserGroup.GROUP_TYPE group_type);
}
