package com.soshinemedia.rest.web;

import com.soshinemedia.rest.Service.ExchangeRateService;
import com.soshinemedia.rest.domain.Brand;
import com.soshinemedia.rest.domain.ExchangeRate;
import com.soshinemedia.rest.domain.Offer;
import com.soshinemedia.rest.repository.ExchangeRateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/rates")
public class ExchangeRateController {

    private ExchangeRateService exchangeRates;

    //public ExchangeRateController(ExchangeRateRepository exchangeRates) {
    //    this.exchangeRates = exchangeRates;
    //}


    //@GetMapping("")
    /*public ResponseEntity<List<ExchangeRate>> all(@RequestParam(name = "brand", required = false) String[] brands) {
        return ok(this.exchangeRates.findAll());
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRate> get(@PathVariable("id") Long id) {
        //return ok(this.exchangeRates.findById(id));
        ExchangeRate n = new ExchangeRate();
        return ok(n);
    }
    @GetMapping("")
    public ResponseEntity<List<Optional<ExchangeRate>>> all() {
        return ok(this.exchangeRates.findAll());
    }
}
