package com.soshinemedia.auth.service.implementation;

import com.soshinemedia.auth.model.Profile;
import com.soshinemedia.auth.model.User;
import com.soshinemedia.auth.repository.ProfileRepository;
import com.soshinemedia.auth.repository.UserRepository;
import com.soshinemedia.auth.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nydiarra on 07/05/17.
 */
@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<Profile> findAllProfiles() {
        return (List< Profile>)profileRepository.findAll();
    }
}