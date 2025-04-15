package com.example.invoice.service;

import com.example.invoice.dto.InvoiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
    Page<InvoiceDTO> getAllInvoices(Pageable pageable);
    InvoiceDTO getInvoiceById(Long id);
    InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);
    void deleteInvoice(Long id);

    Page<InvoiceDTO> searchInvoices(String invoiceNumber, String clientName, String status, Pageable pageable);

    Page<InvoiceDTO> getInvoicesByStatus(String status, Pageable pageable);

    Page<InvoiceDTO> getOverdueInvoices(Pageable pageable);


}
