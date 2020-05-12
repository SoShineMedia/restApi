package com.soshinemedia.auth.web;

import com.soshinemedia.auth.domain.Brand;
import com.soshinemedia.auth.domain.Firm;
import com.soshinemedia.auth.domain.Vehicle;
import com.soshinemedia.auth.repository.FirmRepository;
import com.soshinemedia.auth.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.noContent;
@RestController
@RequestMapping("/v1/lawfirms")
public class FirmController {
    private FirmRepository firms;

    public FirmController(FirmRepository firms) {
        this.firms = firms;
    }

    @GetMapping("")
    public ResponseEntity<List<Firm>> all(@RequestParam(name = "brand", required = false) String[] brands) {
        if (brands == null || brands.length == 0) {
            return ok(this.firms.findAll());
        } else {

            return ok(this.firms.findAll());
        }

    }


    @SuppressWarnings("rawtypes")
    @PostMapping("")
    public ResponseEntity save(@RequestBody FirmForm form, HttpServletRequest request) {
       Firm saved = this.firms.save(Firm.builder().name(form.getName()).build());
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/firm/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Firm> get(@PathVariable("id") Long id) {
        return ok(this.firms.findById(id).orElseThrow(() -> new FirmNotFoundException()));
    }

    /*
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
    }*/
}
