package com.soshinemedia.auth.Db;

import com.soshinemedia.auth.Model.User;
import com.soshinemedia.auth.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("BilboBaggins", "burglar")));
            log.info("Preloading " + repository.save(new User("FrodoBaggins", "thief")));
        };
    }
}
