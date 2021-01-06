package com.soshinemedia.rest.web;

import com.soshinemedia.rest.domain.Firm;
import com.soshinemedia.rest.repository.FirmRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/lawfirms")
public class ExchangeController {
    private FirmRepository firms;

    public ExchangeController(FirmRepository firms) {
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
