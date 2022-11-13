package com.adi.corkproject.config;

import com.adi.corkproject.model.UserGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserGroupConfiguration {

    @Bean
    public UserGroup studentGroup(){
        return new UserGroup(UserGroup.GROUP_TYPE.STUDENT);
    }
    @Bean
    public UserGroup teacherGroup(){
        return new UserGroup(UserGroup.GROUP_TYPE.TEACHER);
    }
}
