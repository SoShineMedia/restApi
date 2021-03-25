package com.soshinemedia.pyngoapi.repository;

import com.soshinemedia.pyngoapi.domain.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findById(Long id);
    Optional<ExchangeRate> findTopByOrderByIdDesc();
}
