package com.soshinemedia.pyngoapi.web;

import com.soshinemedia.pyngoapi.Service.ExchangeRateService;
import com.soshinemedia.pyngoapi.domain.ExchangeRate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity all() {
        return ok("success");
    }
}
