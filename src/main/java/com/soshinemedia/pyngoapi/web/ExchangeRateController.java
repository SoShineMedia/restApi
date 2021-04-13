package com.soshinemedia.pyngoapi.web;

import com.soshinemedia.pyngoapi.Service.ExchangeRateService;
import com.soshinemedia.pyngoapi.Service.TransactionService;
import com.soshinemedia.pyngoapi.domain.ExchangeRate;
import com.soshinemedia.pyngoapi.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/rates")
public class ExchangeRateController {

    private ExchangeRateService exchangeRates;
    @Autowired
    ExchangeRateRepository exchange;
    @Autowired
    TransactionService transactionService;
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
    public ResponseEntity latest() {
        BigDecimal exchangeRate = this.transactionService.exchangeRate();
        return ok(exchangeRate);
    }
}
