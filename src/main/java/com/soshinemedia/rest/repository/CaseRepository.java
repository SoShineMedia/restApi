package com.soshinemedia.rest.repository;

import com.soshinemedia.rest.domain.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "cases", collectionResourceRel = "cases", itemResourceRel = "cases")
public interface CaseRepository extends JpaRepository<Case, Long> {
    //List<Vehicle> findByBrandIn(List<Brand> brandList);
}