package com.soshinemedia.rest.repository;

import com.soshinemedia.rest.domain.ExchangeRate;
import com.soshinemedia.rest.domain.User;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {
    Optional<ExchangeRate> findById(Long id);
    List<Optional<ExchangeRate>> findAll();
}
