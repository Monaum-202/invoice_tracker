package com.example.invoice.repository;

import com.example.invoice.entity.Tax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    Optional<Tax> findByTaxType(String taxType);
    Page<Tax> findAll(Pageable pageable);
}
