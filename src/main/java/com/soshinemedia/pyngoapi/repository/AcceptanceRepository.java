package com.soshinemedia.pyngoapi.repository;

import com.soshinemedia.pyngoapi.domain.Acceptance;
import com.soshinemedia.pyngoapi.domain.Offer;
import com.soshinemedia.pyngoapi.domain.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(path = "acceptances", collectionResourceRel = "acceptances", itemResourceRel = "aacceptance")
public interface AcceptanceRepository extends CrudRepository<Acceptance, Long> {
    List<Acceptance> findByOfferId(Offer id);

    List<Acceptance> findByUserId(Profile id);
}
