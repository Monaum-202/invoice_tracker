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

import java.time.LocalDate;
import java.util.List;

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
        // Convert DTO to Entity
        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);

        // Save first to generate ID
        invoice = invoiceRepository.save(invoice);

        // Now generate invoice number
        invoice.setInvoiceNumber(generateInvoiceNumber(invoice.getId()));

        // Save again with the generated invoice number
        invoice = invoiceRepository.save(invoice);


        if (invoice.getClient() != null) {
            clientRepository.save(invoice.getClient());
        }

        return invoiceMapper.toDTO(invoice);
    }

    private String generateInvoiceNumber(Long id) {
        LocalDate now = LocalDate.now();
        String year = String.format("%02d", now.getYear() % 100);
        String month = String.format("%02d", now.getMonthValue());
        String day = String.format("%02d", now.getDayOfMonth());
        String formattedId = String.format("%04d", id);

        return "INV" + year + month + day + formattedId;
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

    @Override
    public Page<InvoiceDTO> getInvoicesByStatus(String status, Pageable pageable) {
        return invoiceRepository.findByStatus(status.toUpperCase(), pageable)
                .map(invoiceMapper::toDTO);
    }

    @Override
    public Page<InvoiceDTO> getOverdueInvoices(Pageable pageable) {
        return invoiceRepository.findOverdueInvoices(pageable)
                .map(invoiceMapper::toDTO);
    }

    public Double getTotalAmountByClientId(Long clientId) {
        return invoiceRepository.findTotalAmountByClientId(clientId);
    }

    public Double getTotalPaidByClientId(Long clientId) {
        return invoiceRepository.findTotalAmountByClientId(clientId);
    }

    public Double getTotalDueByClientId(Long clientId) {
        return invoiceRepository.findTotalAmountByClientId(clientId);
    }


    public List<Double> getTotalAmountsByUserName(String userName) {
        return invoiceRepository.findTotalAmountsByUserName(userName);
    }

    public List<Double> getTotalPaidAmountsByUserName(String userName) {
        return invoiceRepository.findTotalPaidAmountByUserName(userName);
    }

    public List<Double> getTotalDueAmountsByUserName(String userName) {
        return invoiceRepository.findTotalDueAmountByUserName(userName);
    }



}
