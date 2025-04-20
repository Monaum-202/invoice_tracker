package com.example.invoice.mapper;

import com.example.invoice.dto.BusinessInfoDTO;
import com.example.invoice.entity.BusinessInfo;
import com.example.invoice.entity.security.Users;
import com.example.invoice.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BusinessInfoMapper {

    @Autowired
    private UserRepository usersRepository;


    public BusinessInfoDTO toDTO(BusinessInfo entity) {
        BusinessInfoDTO dto = new BusinessInfoDTO();
        dto.setId(entity.getId());
        dto.setBusinessName(entity.getBusinessName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setWebsite(entity.getWebsite());
        dto.setTaxId(entity.getTaxId());
        dto.setCreatedBy(entity.getCreatedBy().getUserName());

        if (entity.getLogo() != null) {
            dto.setLogoBase64(Base64.getEncoder().encodeToString(entity.getLogo()));
        }

        return dto;
    }

    public BusinessInfo toEntity(BusinessInfoDTO dto) {
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

        if (dto.getCreatedBy() != null) {
            Users user = usersRepository.findByUserName(dto.getCreatedBy()).orElseThrow(() -> new RuntimeException("User not found: " + dto.getCreatedBy()));
            entity.setCreatedBy(user);
        }

        return entity;
    }
}
