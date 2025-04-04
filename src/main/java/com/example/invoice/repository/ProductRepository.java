package com.example.invoice.repository;

import com.example.invoice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductCode(String productCode);
    boolean existsByProductCode(String productCode);

    Page<Product> findAll(Pageable pageable);
}
