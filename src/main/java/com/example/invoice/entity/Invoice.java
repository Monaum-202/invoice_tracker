package com.example.invoice.entity;

import com.example.invoice.entity.security.Users;
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
    private Double paidAmount;
    private Double dueAmount;
    private String status; // PENDING, PAID
    private LocalDateTime issueDate;
    private LocalDate dueDate;

    private String companyName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_name")
    private Users createdBy;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items;


    // Automatically generate invoice number after saving
    @PostPersist
    public void generateInvoiceNumber() {
        if (this.invoiceNumber == null && this.id != null) {
            LocalDate now = LocalDate.now();
            String currentYear = String.format("%02d", now.getYear() % 100);
            String currentMonth = String.format("%02d", now.getMonthValue());
            String currentDay = String.format("%02d", now.getDayOfMonth());
            String formattedId = String.format("%04d", this.id);

            this.invoiceNumber = "INV" + currentYear + currentMonth + currentDay + formattedId;
        }
    }

    public void calculateTotalAmount() {
        double discountPercentage = this.discountPersentage != null ? this.discountPersentage : 0.0;
        double discountCash = this.discountCash != null ? this.discountCash : 0.0;
        double subtotal = this.subtotal != null ? this.subtotal : 0.0;

        double discountAmount = (subtotal * discountPercentage) / 100;
        this.totalAmount = subtotal - discountAmount - discountCash;
    }

    public void calculateDueAmount() {
        double paid = this.paidAmount != null ? this.paidAmount : 0.0;
        double total = this.totalAmount != null ? this.totalAmount : 0.0;
        this.dueAmount = total - paid;
    }

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Double getDiscountPersentage() { return discountPersentage; }
    public void setDiscountPersentage(Double discountPersentage) { this.discountPersentage = discountPersentage; }

    public Double getDiscountCash() { return discountCash; }
    public void setDiscountCash(Double discountCash) { this.discountCash = discountCash; }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }

    public Double getDueAmount() { return dueAmount; }
    public void setDueAmount(Double dueAmount) { this.dueAmount = dueAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDateTime issueDate) { this.issueDate = issueDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Users getCreatedBy() { return createdBy; }
    public void setCreatedBy(Users createdBy) { this.createdBy = createdBy; }

    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }

    public List<InvoiceItem> getItems() { return items; }
    public void setItems(List<InvoiceItem> items) { this.items = items; }

//    public BusinessInfo getBusinessInfo() { return businessInfo; }
//    public void setBusinessInfo(BusinessInfo businessInfo) { this.businessInfo = businessInfo; }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
