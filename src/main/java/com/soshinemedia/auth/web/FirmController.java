package com.soshinemedia.auth.web;

import com.soshinemedia.auth.domain.Brand;
import com.soshinemedia.auth.domain.Vehicle;
import com.soshinemedia.auth.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.noContent;

public class FirmController {
    private FirmRepository firms;

    public VehicleController(FirmRepository firms) {
        this.firms = firms;
    }


    @GetMapping("")
    public ResponseEntity<List<Firm>> all(@RequestParam(name = "brand", required = false) String[] brands) {
        if (brands == null || brands.length == 0) {
            return ok(this.firms.findAll());
        } else {
            List<Brand> brandList = new ArrayList<Brand>();
            for (String brand : brands) {
                brandList.add(Brand.valueOf(brand.toUpperCase()));
            }
            return ok(this.firms.findByBrandIn(brandList));
        }

    }

    @SuppressWarnings("rawtypes")
    @PostMapping("")
    public ResponseEntity save(@RequestBody VehicleForm form, HttpServletRequest request) {
        Vehicle saved = this.firms.save(Vehicle.builder().name(form.getName()).build());
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/vehicles/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> get(@PathVariable("id") Long id) {
        return ok(this.firms.findById(id).orElseThrow(() -> new VehicleNotFoundException()));
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody VehicleForm form) {
        Vehicle existed = this.firms.findById(id).orElseThrow(() -> new VehicleNotFoundException());
        existed.setName(form.getName());

        this.firms.save(existed);
        return noContent().build();
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Vehicle existed = this.firms.findById(id).orElseThrow(() -> new VehicleNotFoundException());
        this.firms.delete(existed);
        return noContent().build();
    }
}
