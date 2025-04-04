package com.example.invoice.dto;

public class ProductDTO {

    private Long id;
    private String productCode;
    private String name;
    private String description;
    private Double unitPrice;
    private Double taxRate;

    // Constructors
    public ProductDTO() {}

    public ProductDTO(Long id, String productCode, String name, String description, Double unitPrice, Double taxRate) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.taxRate = taxRate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return unitPrice;
    }

    public void setPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }


}
