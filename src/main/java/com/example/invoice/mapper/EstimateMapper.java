package com.example.invoice.mapper;

import com.example.invoice.dto.EstimateDTO;
import com.example.invoice.entity.Estimate;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EstimateMapper {

    private final EstimateItemMapper estimateItemMapper;

    public EstimateMapper(EstimateItemMapper estimateItemMapper) {
        this.estimateItemMapper = estimateItemMapper;
    }

    public EstimateDTO toDTO(Estimate estimate) {
        if (estimate == null) return null;

        EstimateDTO dto = new EstimateDTO();
        dto.setId(estimate.getId());
        dto.setEstimateNumber(estimate.getEstimateNumber());
        dto.setIssueDate(estimate.getIssueDate());
        dto.setValidUntil(estimate.getValidUntil());
        dto.setTotalAmount(estimate.getTotalAmount());
        dto.setStatus(estimate.getStatus());
        dto.setClientName(estimate.getClientName());
        dto.setItems(estimate.getItems().stream().map(estimateItemMapper::toDTO).collect(Collectors.toList()));

        return dto;
    }

    public Estimate toEntity(EstimateDTO dto) {
        if (dto == null) return null;

        Estimate estimate = new Estimate();
        estimate.setId(dto.getId());
        estimate.setEstimateNumber(dto.getEstimateNumber());
        estimate.setIssueDate(dto.getIssueDate());
        estimate.setValidUntil(dto.getValidUntil());
        estimate.setTotalAmount(dto.getTotalAmount());
        estimate.setStatus(dto.getStatus());
        estimate.setClientName(dto.getClientName());

        return estimate;
    }
}