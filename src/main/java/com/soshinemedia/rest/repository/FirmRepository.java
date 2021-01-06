package com.soshinemedia.rest.repository;

import com.soshinemedia.rest.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "firms", collectionResourceRel = "firms", itemResourceRel = "firm")
public interface FirmRepository extends JpaRepository<Firm, Long> {
    //List<Vehicle> findByBrandIn(List<Brand> brandList);
}
