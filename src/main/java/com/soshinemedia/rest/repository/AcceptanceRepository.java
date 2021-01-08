package com.soshinemedia.rest.repository;

import com.soshinemedia.rest.domain.Acceptance;
import com.soshinemedia.rest.domain.Brand;
import com.soshinemedia.rest.domain.Offer;
import com.soshinemedia.rest.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(path = "acceptances", collectionResourceRel = "acceptances", itemResourceRel = "aacceptance")
public interface AcceptanceRepository extends CrudRepository<Acceptance, Long> {
    List<Acceptance> findByOfferId(Offer id);

    List<Acceptance> findByUserId(Profile id);
}
