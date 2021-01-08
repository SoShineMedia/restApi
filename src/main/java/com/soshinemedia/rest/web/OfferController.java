package com.soshinemedia.rest.web;

import com.soshinemedia.rest.Service.OfferService;
import com.soshinemedia.rest.domain.Acceptance;
import com.soshinemedia.rest.domain.Offer;
import com.soshinemedia.rest.repository.AcceptanceRepository;
import com.soshinemedia.rest.repository.OfferRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1")

public class OfferController {

    private OfferService offer;
    private AcceptanceRepository acceptances;

    public OfferController(AcceptanceRepository acceptances) {
        this.acceptances = acceptances;
    }

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> all(@RequestParam(name = "brand", required = false) String[] brands) {
            return ok(this.offer.findAll());
    }

    @GetMapping("/offers/{id}/acceptances")
    public ResponseEntity<List<Acceptance>> getAcceptances(@PathVariable("id") Long id, @RequestParam(name = "brand", required = false) String[] brands) {

            return ok(this.offer.findAllAcceptances());

    }


}