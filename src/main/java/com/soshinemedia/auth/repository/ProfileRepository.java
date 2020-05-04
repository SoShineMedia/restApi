package com.soshinemedia.auth.repository;

import com.soshinemedia.auth.model.Profile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nydiarra on 10/05/17.
 */
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
