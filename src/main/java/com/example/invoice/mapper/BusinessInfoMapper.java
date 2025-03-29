package com.example.invoice.mapper;

import com.example.invoice.dto.BusinessInfoDTO;
import com.example.invoice.entity.BusinessInfo;
import org.springframework.stereotype.Component;

@Component
public class BusinessInfoMapper {

    public BusinessInfoDTO toDTO(BusinessInfo businessInfo) {
        if (businessInfo == null) return null;

        return new BusinessInfoDTO(
                businessInfo.getId(),
                businessInfo.getBusinessName(),
                businessInfo.getAddress(),
                businessInfo.getPhone(),
                businessInfo.getWebsite(),
                businessInfo.getEmail(),
                businessInfo.getTaxId()
        );
    }

    public BusinessInfo toEntity(BusinessInfoDTO businessInfoDTO) {
        if (businessInfoDTO == null) return null;

        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setId(businessInfoDTO.getId());
        businessInfo.setBusinessName(businessInfoDTO.getBusinessName());
        businessInfo.setAddress(businessInfoDTO.getAddress());
        businessInfo.setPhone(businessInfoDTO.getPhone());
        businessInfo.setWebsite(businessInfoDTO.getWebsite());
        businessInfo.setEmail(businessInfoDTO.getEmail());
        businessInfo.setTaxId(businessInfoDTO.getTaxId());
        return businessInfo;
    }
}
