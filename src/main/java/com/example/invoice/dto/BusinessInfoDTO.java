package com.example.invoice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BusinessInfoDTO {
    private Long id;
    private String businessName;
    private String address;
    private String phone;
    private String website;
    private String email;
    private String taxId;

    // Base64 encoded image string for logo
    private String logoBase64;




    public BusinessInfoDTO() {
    }

    public BusinessInfoDTO(Long id, String businessName, String address, String phone,
                           String email, String taxId, String website, String logoBase64) {
        this.id = id;
        this.businessName = businessName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.taxId = taxId;
        this.website = website;
        this.logoBase64 = logoBase64;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getLogoBase64() {
        return logoBase64;
    }

    public void setLogoBase64(String logoBase64) {
        this.logoBase64 = logoBase64;
    }
}
