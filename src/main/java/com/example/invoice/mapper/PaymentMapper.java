package com.example.invoice.mapper;

import com.example.invoice.dto.PaymentDTO;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDTO toDTO(Payment payment) {
        if (payment == null) {
            return null;
        }
        return new PaymentDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getInvoice().getId()
        );
    }

    public Payment toEntity(PaymentDTO paymentDTO, Invoice invoice) {
        if (paymentDTO == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setInvoice(invoice);
        return payment;
    }
}
