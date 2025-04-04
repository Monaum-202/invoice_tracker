package com.example.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstimateDTO {
    private Long id;
    private String estimateNumber;
    private LocalDate issueDate;
    private LocalDate validUntil;
    private Double totalAmount;
    private String status;
    private String clientName;
    private List<EstimateItemDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstimateNumber() {
        return estimateNumber;
    }

    public void setEstimateNumber(String estimateNumber) {
        this.estimateNumber = estimateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<EstimateItemDTO> getItems() {
        return items;
    }

    public void setItems(List<EstimateItemDTO> items) {
        this.items = items;
    }
}