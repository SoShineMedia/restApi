package com.soshinemedia.auth.service;

import com.soshinemedia.auth.model.Profile;
import com.soshinemedia.auth.model.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

    List<Profile> findAllProfiles();
}