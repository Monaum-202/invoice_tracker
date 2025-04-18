package com.example.invoice.mapper;

import com.example.invoice.dto.BusinessInfoDTO;
import com.example.invoice.entity.BusinessInfo;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BusinessInfoMapper {

    public static BusinessInfoDTO toDTO(BusinessInfo entity) {
        BusinessInfoDTO dto = new BusinessInfoDTO();
        dto.setId(entity.getId());
        dto.setBusinessName(entity.getBusinessName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setWebsite(entity.getWebsite());
        dto.setTaxId(entity.getTaxId());

        if (entity.getLogo() != null) {
            dto.setLogoBase64(Base64.getEncoder().encodeToString(entity.getLogo()));
        }

        return dto;
    }

    public static BusinessInfo toEntity(BusinessInfoDTO dto) {
        BusinessInfo entity = new BusinessInfo();
        entity.setId(dto.getId());
        entity.setBusinessName(dto.getBusinessName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setWebsite(dto.getWebsite());
        entity.setTaxId(dto.getTaxId());

        if (dto.getLogoBase64() != null) {
            entity.setLogo(Base64.getDecoder().decode(dto.getLogoBase64()));
        }

        return entity;
    }
}
