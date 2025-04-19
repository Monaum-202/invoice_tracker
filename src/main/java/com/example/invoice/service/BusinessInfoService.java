package com.example.invoice.service;

import com.example.invoice.dto.BusinessInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BusinessInfoService {
    BusinessInfoDTO saveBusinessInfo(BusinessInfoDTO dto);
    BusinessInfoDTO getBusinessInfo(Long id);
    BusinessInfoDTO updateLogo(Long id, MultipartFile file) throws IOException;
    BusinessInfoDTO getBusinessInfoByUsername(String username);
}
