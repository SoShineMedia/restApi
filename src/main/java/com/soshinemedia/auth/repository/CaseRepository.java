package com.soshinemedia.auth.repository;

import com.soshinemedia.auth.domain.Case;
import com.soshinemedia.auth.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "cases", collectionResourceRel = "cases", itemResourceRel = "cases")
public interface CaseRepository extends JpaRepository<Case, Long> {
    //List<Vehicle> findByBrandIn(List<Brand> brandList);
}