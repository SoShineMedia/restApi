package com.soshinemedia.rest.Service;

import com.soshinemedia.rest.domain.ExchangeRate;
import com.soshinemedia.rest.domain.Offer;
import com.soshinemedia.rest.repository.ExchangeRateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExchangeRateService {
    private ExchangeRateRepository exchangeRateRepository;
    public List <Optional<ExchangeRate>> findAll() {

        List <Optional<ExchangeRate>> it = exchangeRateRepository.findAll();

        return it;
    }
    /*public  <Optional<ExchangeRate>> findById(Long id) {
        return {};
    }*/
}
