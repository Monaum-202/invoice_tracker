package com.example.invoice.service.impl;

import com.example.invoice.dto.BusinessInfoDTO;
import com.example.invoice.entity.BusinessInfo;
import com.example.invoice.mapper.BusinessInfoMapper;
import com.example.invoice.repository.BusinessInfoRepository;
import com.example.invoice.service.BusinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BusinessInfoServiceImpl implements BusinessInfoService {

    @Autowired
    private BusinessInfoRepository businessInfoRepository;

    @Autowired
    private BusinessInfoMapper businessInfoMapper;


    @Override
    public BusinessInfoDTO saveBusinessInfo(BusinessInfoDTO dto) {
        BusinessInfo entity = businessInfoMapper.toEntity(dto);
        BusinessInfo saved = businessInfoRepository.save(entity);
        return businessInfoMapper.toDTO(saved);
    }


    @Override
    public BusinessInfoDTO getBusinessInfo(Long id) {
        BusinessInfo entity = businessInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusinessInfo not found"));
        return businessInfoMapper.toDTO(entity);
    }

    @Override
    public BusinessInfoDTO updateLogo(Long id, MultipartFile file) throws IOException {
        BusinessInfo entity = businessInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusinessInfo not found"));

        entity.setLogo(file.getBytes());
        BusinessInfo updated = businessInfoRepository.save(entity);
        return businessInfoMapper.toDTO(updated);
    }

    @Override
    public BusinessInfoDTO getBusinessInfoByUsername(String username) {
        BusinessInfo entity = businessInfoRepository
                .findFirstByCreatedBy_UserName(username)
                .orElseThrow(() -> new RuntimeException("Business info not found for username: " + username));
        return businessInfoMapper.toDTO(entity);
    }

}

