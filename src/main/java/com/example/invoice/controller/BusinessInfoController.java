package com.example.invoice.controller;

import com.example.invoice.dto.BusinessInfoDTO;
import com.example.invoice.service.BusinessInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/business-info")
public class BusinessInfoController {
    @Autowired
    private BusinessInfoService businessInfoService;

    @PostMapping
    public ResponseEntity<BusinessInfoDTO> create(@RequestBody BusinessInfoDTO dto) {
        return ResponseEntity.ok(businessInfoService.saveBusinessInfo(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessInfoDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(businessInfoService.getBusinessInfo(id));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<BusinessInfoDTO> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(businessInfoService.getBusinessInfoByUsername(username));
    }


    @PostMapping("/upload-logo/{id}")
    public ResponseEntity<BusinessInfoDTO> uploadLogo(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(businessInfoService.updateLogo(id, file));
    }
}
