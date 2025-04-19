package com.example.invoice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class InvoiceDTO {
    private Long id;
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private Double subtotal;
    private Double discountPersentage;
    private Double discountCash;
    private Double totalAmount;
    private Double paidAmount;
    private Double dueAmount;
    private LocalDate dueDate;
    private String status; // PENDING, PAID, OVERDUE
    private ClientDTO client;
    private String createdBy;
    private List<InvoiceItemDTO> items; // Invoice items
    private BusinessInfoDTO businessInfo;
    private String companyName;


    // Custom constructor to accept all the fields
    public InvoiceDTO(Long id, String invoiceNumber, LocalDateTime issueDate, Double subtotal, Double totalAmount,
                      Double discountPersentage, Double discountCash, Double paidAmount, Double dueAmount, LocalDate dueDate, String status, ClientDTO client, String companyName,
                      String createdBy, List<InvoiceItemDTO> items) {

        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.subtotal = subtotal;
        this.totalAmount = totalAmount;
        this.discountPersentage = discountPersentage;
        this.discountCash = discountCash;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.dueDate = dueDate;
        this.status = status;
        this.client = client;
        this.companyName = companyName;
        this.createdBy = createdBy;
        this.items = items;

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

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<InvoiceItemDTO> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemDTO> items) {
        this.items = items;
    }

    public BusinessInfoDTO getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfoDTO businessInfo) {
        this.businessInfo = businessInfo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
