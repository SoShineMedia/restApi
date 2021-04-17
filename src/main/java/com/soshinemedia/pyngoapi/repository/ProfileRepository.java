package com.soshinemedia.pyngoapi.repository;

import com.soshinemedia.pyngoapi.domain.Profile;
//import jdk.internal.jline.internal.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@RepositoryRestResource(path = "profiles", collectionResourceRel = "profiles", itemResourceRel = "profile")
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id );

    Optional<Profile> findByUserId(Long id );

    Optional<Profile> findByBalance(Float id );

    Optional<Profile> findByAccountNumber(String num );

    //Optional<Profile> findByAccountNumber(String address);


}
