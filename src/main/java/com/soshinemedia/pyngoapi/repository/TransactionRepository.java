package com.soshinemedia.pyngoapi.repository;

import com.soshinemedia.pyngoapi.domain.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id );

    List<Transaction> findByFromAddressOrToAddress(String fromAddress, String toAddress, Pageable pageInfo);

}
