package com.adi.corkproject.config;

import com.adi.corkproject.exception.UserExistingException;
import com.adi.corkproject.model.UserGroup;
import com.adi.corkproject.service.UserGroupService;
import com.adi.corkproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final UserService userService;
    private final UserGroupService userGroupService;
    private final UserGroupConfiguration userGroupConfiguration;

    public LoadDatabase(UserService userService, UserGroupService userGroupService, UserGroupConfiguration userGroupConfiguration) {
        this.userService = userService;
        this.userGroupService = userGroupService;
        this.userGroupConfiguration = userGroupConfiguration;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        var studentGroup = userGroupService.save(userGroupConfiguration.studentGroup().getGroupType());
        var teacherGroup = userGroupService.save(userGroupConfiguration.teacherGroup().getGroupType());
        try {
            log.info("Preloading " + userService.save("maciejpilarski@gmail.com", "maciejos", "maciekpass", studentGroup));
            log.info("Preloading " + userService.save("medusaofficial@gmail.com", "medusaofficial", "medusapass", studentGroup));
            log.info("Preloading " + userService.save("adrianoitaliano@gmail.com", "adriaaan7", "adrianpassw", teacherGroup));
            log.info("Preloading " + studentGroup.getUser());
            log.info("Preloading " + teacherGroup.getUser());
        }catch (UserExistingException exception){
            exception.printStackTrace();
        }
    }
}
