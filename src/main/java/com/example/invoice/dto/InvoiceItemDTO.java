package com.example.invoice.dto;

import lombok.Data;

@Data
public class InvoiceItemDTO {
    private Long id;
    private String itemName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private Long invoiceId;  // Keep as Long

    public InvoiceItemDTO(Long id, String itemName, Integer quantity, Double unitPrice, Double totalPrice, Long invoiceId) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.invoiceId = invoiceId;  // Assign directly as Long
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
