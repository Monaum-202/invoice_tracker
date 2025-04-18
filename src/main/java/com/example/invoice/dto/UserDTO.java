package com.example.invoice.dto;

import lombok.Data;
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String role;
    private BusinessInfoDTO businessInfo;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String role, BusinessInfoDTO businessInfo) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.businessInfo = businessInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BusinessInfoDTO getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfoDTO businessInfo) {
        this.businessInfo = businessInfo;
    }
}