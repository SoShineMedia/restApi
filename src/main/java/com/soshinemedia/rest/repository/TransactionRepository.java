package com.soshinemedia.rest.repository;

import com.soshinemedia.rest.domain.Profile;
import com.soshinemedia.rest.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id );
}
