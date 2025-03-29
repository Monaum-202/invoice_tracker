package com.example.invoice.service;

import com.example.invoice.dto.ReportDTO;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.Report;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;



    public ReportDTO generateReport(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        Double amountPaid = invoice.getItems().stream()
                .mapToDouble(item -> item.getTotalPrice() != null ? item.getTotalPrice() : 0.0)
                .sum();

        Report report = new Report();
        report.setReportDate(LocalDate.now());
        report.setReportType("INVOICE");
        report.setTotalRevenue(amountPaid);
        report = reportRepository.save(report);

        // ✅ Use setter-based initialization instead
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setInvoiceId(invoice.getId());
        reportDTO.setClientName(invoice.getClient().getName());
        reportDTO.setIssueDate(LocalDate.now());
        reportDTO.setDueDate(invoice.getDueDate());
        reportDTO.setTotalAmount(invoice.getTotalAmount());
        reportDTO.setAmountPaid(amountPaid);
        reportDTO.setStatus(invoice.getStatus());

        return reportDTO;
    }

}
