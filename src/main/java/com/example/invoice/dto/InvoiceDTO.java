package com.example.invoice.dto;

import com.example.invoice.entity.User;
import lombok.AllArgsConstructor;
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
    private Double taxRate;
    private Double discountPersentage;
    private Double discountCash;
    private Double totalAmount;
    private LocalDate dueDate;
    private String status; // PENDING, PAID, OVERDUE
    private Long clientId; // Client associated with the invoice
    private Long createdBy;
    private List<InvoiceItemDTO> items; // Invoice items
    private BusinessInfoDTO businessInfo;

    // Custom constructor to accept all the fields
    public InvoiceDTO(Long id, LocalDateTime issueDate, Double subtotal, Double taxRate,
                      Double discountPersentage, Double discountCash, Double totalAmount, LocalDate dueDate, String status, Long clientId,
                      Long createdBy, List<InvoiceItemDTO> items, BusinessInfoDTO businessInfo) {

        this.id = id;

        LocalDate currentDate = LocalDate.now();
        String currentYear = String.format("%02d", currentDate.getYear() % 100);  // Last two digits of the year
        String currentMonth = String.format("%02d", currentDate.getMonthValue());  // MM format (e.g., "03" for March)
        String formattedId = String.format("%04d", id);
        this.invoiceNumber = "INV" + currentYear + currentMonth + formattedId;

        this.issueDate = issueDate;
        this.subtotal = subtotal;
        this.taxRate = taxRate;
        this.discountPersentage = discountPersentage;
        this.discountCash = discountCash;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.status = status;
        this.clientId = clientId;
        this.createdBy = createdBy;
        this.items = items;
        this.businessInfo = businessInfo;
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

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
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
}
