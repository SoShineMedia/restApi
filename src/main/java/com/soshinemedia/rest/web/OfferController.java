package com.soshinemedia.rest.web;

import com.soshinemedia.rest.domain.Case;
import com.soshinemedia.rest.repository.CaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/cases")
public class OfferController {

    private CaseRepository cases;

    public OfferController(CaseRepository cases) {
        this.cases = cases;
    }

    @GetMapping("")
    public ResponseEntity<List<Case>> all(@RequestParam(name = "brand", required = false) String[] brands) {
        if (brands == null || brands.length == 0) {
            return ok(this.cases.findAll());
        } else {

            return ok(this.cases.findAll());
        }
    }
}