package com.example.invoice.repository;

import com.example.invoice.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByInvoiceId(Long invoiceId, Pageable pageable);

    @Query("SELECT p FROM Payment p WHERE " +
            "(:clientId IS NULL OR p.client.id = :clientId) " +
            "AND (:invoiceId IS NULL OR p.invoice.id = :invoiceId) " +
            "AND (:paymentDate IS NULL OR p.paymentDate = :paymentDate) " +
            "AND (:paymentMethod IS NULL OR p.paymentMethod LIKE %:paymentMethod%)")
    Page<Payment> searchPayments(
            @Param("clientId") Long clientId,
            @Param("invoiceId") Long invoiceId,
            @Param("paymentDate") LocalDateTime paymentDate,
            @Param("paymentMethod") String paymentMethod,
            Pageable pageable);
}
