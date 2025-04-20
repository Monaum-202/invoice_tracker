package com.example.invoice.entity;

import com.example.invoice.entity.security.Users;
import jakarta.persistence.*;




@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;

    @Column(nullable = false)
    private String name;


    private String description;

    @Column(nullable = false)
    private double unitPrice;


    @Column(nullable = false)
    private double taxRate; // Tax rate for the individual product (in percentage)

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_name")
    private Users createdBy;

    // Constructors
    public Product() {}

    public Product(Long id, String productCode, String name, String description, double unitPrice, double taxRate) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.taxRate = taxRate;
    }


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

    public double getPrice() {
        return unitPrice;
    }

    public void setPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
