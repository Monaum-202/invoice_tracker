package com.example.invoice.service.impl;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.entity.BusinessInfo;
import com.example.invoice.entity.Client;
import com.example.invoice.entity.Invoice;
import com.example.invoice.mapper.InvoiceMapper;
import com.example.invoice.repository.BusinessInfoRepository;
import com.example.invoice.repository.ClientRepository;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private BusinessInfoRepository businessInfoRepository;

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        invoice = invoiceRepository.save(invoice);
        invoice.setInvoiceNumber(invoice.getInvoiceNumber().replace("null",invoice.getId().toString()));

        if (invoice.getBusinessInfo() != null) {
            businessInfoRepository.save(invoice.getBusinessInfo());
        }
        if (invoice.getClient() != null){
            clientRepository.save(invoice.getClient());
        }

        // Then save the invoice
        invoice = invoiceRepository.save(invoice);

        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public Page<InvoiceDTO> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable)
                .map(invoiceMapper::toDTO);
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        existingInvoice.setInvoiceNumber(invoiceDTO.getInvoiceNumber());
        existingInvoice.setIssueDate(invoiceDTO.getIssueDate());
        existingInvoice.setTotalAmount(invoiceDTO.getTotalAmount());

        existingInvoice = invoiceRepository.save(existingInvoice);
        return invoiceMapper.toDTO(existingInvoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Page<InvoiceDTO> searchInvoices(String invoiceNumber, String clientName, String status, Pageable pageable) {
        Page<Invoice> invoices = invoiceRepository.searchInvoices(invoiceNumber, clientName, status, pageable);
        return invoices.map(invoiceMapper::toDTO);
    }
}
