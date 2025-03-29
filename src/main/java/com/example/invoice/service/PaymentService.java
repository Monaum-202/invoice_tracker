package com.example.invoice.service;

import com.example.invoice.dto.PaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    Page<PaymentDTO> getAllPayments(Pageable pageable);
    PaymentDTO getPaymentById(Long id);
    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO);
    void deletePayment(Long id);

    Page<PaymentDTO> searchPayments(Long clientId, Long invoiceId, LocalDateTime paymentDate, String paymentMethod, Pageable pageable);

}

