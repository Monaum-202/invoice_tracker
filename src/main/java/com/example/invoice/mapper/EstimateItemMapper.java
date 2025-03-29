package com.example.invoice.mapper;

import com.example.invoice.dto.EstimateItemDTO;
import com.example.invoice.entity.EstimateItem;
import org.springframework.stereotype.Component;

@Component
public class EstimateItemMapper {

    // Convert EstimateItem Entity to EstimateItemDTO
    public EstimateItemDTO toDTO(EstimateItem estimateItem) {
        if (estimateItem == null) {
            return null;
        }

        EstimateItemDTO dto = new EstimateItemDTO();
        dto.setId(estimateItem.getId());
        dto.setItemName(estimateItem.getItemName());
        dto.setQuantity(estimateItem.getQuantity());
        dto.setUnitPrice(estimateItem.getUnitPrice());
        dto.setTotalPrice(estimateItem.getTotalPrice());
        return dto;
    }

    // Convert EstimateItemDTO to EstimateItem Entity
    public EstimateItem toEntity(EstimateItemDTO dto) {
        if (dto == null) {
            return null;
        }

        EstimateItem estimateItem = new EstimateItem();
        estimateItem.setId(dto.getId());
        estimateItem.setItemName(dto.getItemName());
        estimateItem.setQuantity(dto.getQuantity());
        estimateItem.setUnitPrice(dto.getUnitPrice());
        estimateItem.setTotalPrice(dto.getTotalPrice());
        return estimateItem;
    }

    // Update existing EstimateItem entity with data from EstimateItemDTO
    public void updateEntityFromDTO(EstimateItemDTO dto, EstimateItem estimateItem) {
        if (dto == null || estimateItem == null) {
            return;
        }

        estimateItem.setItemName(dto.getItemName());
        estimateItem.setQuantity(dto.getQuantity());
        estimateItem.setUnitPrice(dto.getUnitPrice());
        estimateItem.setTotalPrice(dto.getTotalPrice());
    }
}