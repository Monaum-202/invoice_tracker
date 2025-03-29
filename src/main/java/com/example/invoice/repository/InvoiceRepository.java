package com.example.invoice.repository;

import com.example.invoice.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    Page<Invoice> findAll(Pageable pageable);


    // Search by invoice number, client name, or status
    @Query("SELECT i FROM Invoice i WHERE " +
            "(:invoiceNumber IS NULL OR i.invoiceNumber LIKE %:invoiceNumber%) " +
            "AND (:clientName IS NULL OR i.client.name LIKE %:clientName%) " +
            "AND (:status IS NULL OR i.status = :status)")
    Page<Invoice> searchInvoices(
            @Param("invoiceNumber") String invoiceNumber,
            @Param("clientName") String clientName,
            @Param("status") String status,
            Pageable pageable);
}
