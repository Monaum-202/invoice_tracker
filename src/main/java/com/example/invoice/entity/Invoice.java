package com.example.invoice.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private Double subtotal;
    private Double totalAmount;
    private Double discountPersentage;
    private Double discountCash;
    private String status; // PENDING, PAID
    private LocalDateTime issueDate;
    private LocalDate dueDate;

    // Many invoices belong to one client
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    // Many invoices can be created by one user
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    // One invoice can have multiple payments
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_info_id")
    private BusinessInfo businessInfo;
    public void calculateTotalAmount() {
        double discountAmount = (this.subtotal * this.discountPersentage)/100;
        this.totalAmount = this.subtotal - discountAmount - this.discountCash ;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }


    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public BusinessInfo getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfo businessInfo) {
        this.businessInfo = businessInfo;
    }

    public Double getDiscountPersentage() {
        return discountPersentage;
    }

    public void setDiscountPersentage(Double discountPersentage) {
        this.discountPersentage = discountPersentage;
    }

    public Double getDiscountCash() {
        return discountCash;
    }

    public void setDiscountCash(Double discountCash) {
        this.discountCash = discountCash;
    }
}
