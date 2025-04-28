package com.example.invoice.controller;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.service.InvoiceService;
import com.example.invoice.service.impl.InvoiceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceServiceImpl invoiceService2;


    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) {
        System.out.println(invoiceDTO.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.createInvoice(invoiceDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }


    @GetMapping
    public ResponseEntity<Page<InvoiceDTO>> getAllInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return ResponseEntity.ok(invoiceService.getAllInvoices(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @RequestBody @Valid InvoiceDTO invoiceDTO) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoiceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public Page<InvoiceDTO> searchInvoices(
            @RequestParam(required = false) String invoiceNumber,
            Pageable pageable) {
        return invoiceService.searchInvoices(invoiceNumber,pageable);
    }


    @GetMapping("/paid")
    public ResponseEntity<Page<InvoiceDTO>> getPaidInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)
        );

        Page<InvoiceDTO> paidInvoices = invoiceService.getInvoicesByStatus("PAID", pageable);
        return ResponseEntity.ok(paidInvoices);
    }

    @GetMapping("/unpaid")
    public ResponseEntity<Page<InvoiceDTO>> getUnpaidInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)
        );

        Page<InvoiceDTO> unpaidInvoices = invoiceService.getInvoicesByStatus("UNPAID", pageable);
        return ResponseEntity.ok(unpaidInvoices);
    }

    @GetMapping("/overdue")
    public ResponseEntity<Page<InvoiceDTO>> getOverdueInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by(direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)
        );

        Page<InvoiceDTO> overdueInvoices = invoiceService.getOverdueInvoices(pageable);
        return ResponseEntity.ok(overdueInvoices);
    }



    @GetMapping("/totals-by-user/{userName}")
    public ResponseEntity<List<Double>> getTotalAmounts(@PathVariable String userName) {
        List<Double> amounts = invoiceService2.getTotalAmountsByUserName(userName);
        return ResponseEntity.ok(amounts);
    }

    @GetMapping("/totals-paid-by-user/{userName}")
    public ResponseEntity<List<Double>> getTotalPaidAmounts(@PathVariable String userName) {
        List<Double> amounts = invoiceService2.getTotalPaidAmountsByUserName(userName);
        return ResponseEntity.ok(amounts);
    }

    @GetMapping("/totals-due-by-user/{userName}")
    public ResponseEntity<List<Double>> getTotalDueAmounts(@PathVariable String userName) {
        List<Double> amounts = invoiceService2.getTotalDueAmountsByUserName(userName);
        return ResponseEntity.ok(amounts);
    }

}
