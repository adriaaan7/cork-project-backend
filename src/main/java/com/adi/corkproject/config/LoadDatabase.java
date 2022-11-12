package com.adi.corkproject.config;

import com.adi.corkproject.model.User;
import com.adi.corkproject.repository.UserRepository;
import com.adi.corkproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //@Bean
    CommandLineRunner initDatabase(UserService userService) {

        return args -> {
            log.info("Preloading " + userService.save("adrianoitaliano@gmail.com", "adriaaan7", "password"));
            log.info("Preloading " + userService.save("medusaofficial@gmail.com", "medusaofficial", "medusapass"));
        };
    }
}
