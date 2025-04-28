package com.example.invoice.repository;

import com.example.invoice.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    Page<Invoice> findAll(Pageable pageable);


    // Search by invoice number, client name, or status
    @Query("SELECT i FROM Invoice i WHERE " +
            "(:invoiceNumber IS NULL OR i.invoiceNumber LIKE %:invoiceNumber%) ")
    Page<Invoice> searchInvoices(
            @Param("invoiceNumber") String invoiceNumber,
            Pageable pageable);



    Page<Invoice> findByStatus(String status, Pageable pageable);

    @Query("SELECT i FROM Invoice i WHERE i.dueDate < CURRENT_DATE AND i.status <> 'PAID'")
    Page<Invoice> findOverdueInvoices(Pageable pageable);

    @Query("SELECT SUM(i.totalAmount) FROM Invoice i WHERE i.client.id = :clientId")
    Double findTotalAmountByClientId(@Param("clientId") Long clientId);

    @Query("SELECT SUM(i.paidAmount) FROM Invoice i WHERE i.client.id = :clientId")
    Double findTotalPaidAmountByClientId(@Param("clientId") Long clientId);

    @Query("SELECT SUM(i.dueAmount) FROM Invoice i WHERE i.client.id = :clientId")
    Double findTotalDueAmountByClientId(@Param("clientId") Long clientId);



    @Query("SELECT SUM(i.totalAmount) FROM Invoice i WHERE i.createdBy.userName = :userName")
    List<Double> findTotalPaidAmountByUserName(@Param("userName") String userName);

    @Query("SELECT SUM(i.paidAmount) FROM Invoice i WHERE i.createdBy.userName = :userName")
    List<Double> findTotalAmountsByUserName(@Param("userName") String userName);

    @Query("SELECT SUM(i.dueAmount) FROM Invoice i WHERE i.createdBy.userName = :userName")
    List<Double> findTotalDueAmountByUserName(@Param("userName") String userName);



}
