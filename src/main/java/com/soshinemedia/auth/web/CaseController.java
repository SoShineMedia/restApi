package com.soshinemedia.auth.web;

import com.soshinemedia.auth.domain.Case;
import com.soshinemedia.auth.domain.Firm;
import com.soshinemedia.auth.repository.CaseRepository;
import com.soshinemedia.auth.repository.FirmRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/cases")
public class CaseController {

    private CaseRepository cases;

    public CaseController(CaseRepository cases) {
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