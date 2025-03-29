package com.example.invoice.service;

import com.example.invoice.dto.EstimateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstimateService {
    EstimateDTO createEstimate(EstimateDTO estimateDTO);
    Page<EstimateDTO> getAllEstimates(Pageable pageable);
    EstimateDTO getEstimateById(Long id);
    EstimateDTO updateEstimate(Long id, EstimateDTO estimateDTO);
    void deleteEstimate(Long id);
}