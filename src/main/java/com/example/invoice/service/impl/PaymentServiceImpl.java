package com.example.invoice.service.impl;


import com.example.invoice.dto.PaymentDTO;
import com.example.invoice.entity.Invoice;

import com.example.invoice.entity.Payment;
import com.example.invoice.mapper.PaymentMapper;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.repository.PaymentRepository;
import com.example.invoice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Invoice invoice = invoiceRepository.findById(paymentDTO.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        Payment payment = paymentMapper.toEntity(paymentDTO, invoice);
        payment = paymentRepository.save(payment);

        return paymentMapper.toDTO(payment);
    }

    @Override
    public Page<PaymentDTO> getAllPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable)
                .map(paymentMapper::toDTO);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return paymentMapper.toDTO(payment);
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Invoice invoice = invoiceRepository.findById(paymentDTO.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setPaymentDate(paymentDTO.getPaymentDate());
        existingPayment.setInvoice(invoice);

        existingPayment = paymentRepository.save(existingPayment);
        return paymentMapper.toDTO(existingPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Page<PaymentDTO> searchPayments(Long clientId, Long invoiceId, LocalDateTime paymentDate, String paymentMethod, Pageable pageable) {
        Page<Payment> payments = paymentRepository.searchPayments(clientId, invoiceId, paymentDate, paymentMethod, pageable);
        return payments.map(paymentMapper::toDTO);
    }
}
