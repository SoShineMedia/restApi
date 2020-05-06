package com.soshinemedia.auth.repository;

import com.soshinemedia.auth.domain.Brand;
import com.soshinemedia.auth.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "vehicles", collectionResourceRel = "vehicles", itemResourceRel = "vehicle")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBrandIn(List<Brand> brandList);
}
