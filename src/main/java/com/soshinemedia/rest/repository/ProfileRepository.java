package com.soshinemedia.rest.repository;

import com.soshinemedia.rest.domain.Profile;
import com.soshinemedia.rest.domain.User;
//import jdk.internal.jline.internal.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//@RepositoryRestResource(path = "profiles", collectionResourceRel = "profiles", itemResourceRel = "profile")
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id );

    Optional<Profile> findByUserId(Long id );

    Optional<Profile> findByBalance(Float id );


}
