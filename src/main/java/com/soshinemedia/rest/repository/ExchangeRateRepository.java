package com.soshinemedia.rest.repository;

import com.soshinemedia.rest.domain.ExchangeRate;
import com.soshinemedia.rest.domain.Transaction;
import com.soshinemedia.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findById(Long id);
    Optional<ExchangeRate> findTopByOrderByIdDesc();
}
