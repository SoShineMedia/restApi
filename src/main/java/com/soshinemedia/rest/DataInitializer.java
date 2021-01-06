package com.soshinemedia.rest;

import com.soshinemedia.rest.domain.Firm;
import com.soshinemedia.rest.domain.User;
import com.soshinemedia.rest.domain.Vehicle;
import com.soshinemedia.rest.repository.FirmRepository;
import com.soshinemedia.rest.repository.UserRepository;
import com.soshinemedia.rest.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    VehicleRepository vehicles;

    @Autowired
    UserRepository users;

    @Autowired
    FirmRepository firms;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.debug("initializing vehicles data...");
        Arrays.asList("moto", "car").forEach(v -> this.vehicles.saveAndFlush(Vehicle.builder().name(v).build()));

        log.debug("printing all vehicles...");
        this.vehicles.findAll().forEach(v -> log.debug(" Vehicle :" + v.toString()));

        log.debug("initializing firms data...");
        Arrays.asList("moto", "car").forEach(v -> this.firms.saveAndFlush(
                Firm.builder()
                        .name(v)
                        .company_number("sample_1")
                        .registered_address("address_first")
                        .address("jsdhfjhsd")
                        .email("when@why.com")
                        .telephone("211434235423")
                        .website("www.php.com")
                        .build()));

        log.debug("printing all firms...");
        //this.firms.findAll().forEach(v -> log.debug(" Firm :" + v.toString()));

        this.users.save(User.builder()
            .username("user")
            .password(this.passwordEncoder.encode("password"))
            .roles(Arrays.asList( "ROLE_USER"))
            .build()
        );

        this.users.save(User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );

        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
