package com.example.invoice.service.impl;

import com.example.invoice.dto.EstimateDTO;
import com.example.invoice.entity.Client;
import com.example.invoice.entity.Estimate;
import com.example.invoice.mapper.EstimateMapper;
import com.example.invoice.repository.ClientRepository;
import com.example.invoice.repository.EstimateRepository;
import com.example.invoice.service.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstimateServiceImpl implements EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EstimateMapper estimateMapper;

    @Override
    public EstimateDTO createEstimate(EstimateDTO estimateDTO) {
        Client client = clientRepository.findById(estimateDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Estimate estimate = estimateMapper.toEntity(estimateDTO);
        estimate.setClient(client);
        estimate = estimateRepository.save(estimate);

        return estimateMapper.toDTO(estimate);
    }

    @Override
    public Page<EstimateDTO> getAllEstimates(Pageable pageable) {
        return estimateRepository.findAll(pageable)
                .map(estimateMapper::toDTO);
    }

    @Override
    public EstimateDTO getEstimateById(Long id) {
        Estimate estimate = estimateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estimate not found"));

        return estimateMapper.toDTO(estimate);
    }

    @Override
    public EstimateDTO updateEstimate(Long id, EstimateDTO estimateDTO) {
        Estimate estimate = estimateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estimate not found"));

        estimate.setEstimateNumber(estimateDTO.getEstimateNumber());
        estimate.setIssueDate(estimateDTO.getIssueDate());
        estimate.setValidUntil(estimateDTO.getValidUntil());
        estimate.setTotalAmount(estimateDTO.getTotalAmount());
        estimate.setStatus(estimateDTO.getStatus());

        estimate = estimateRepository.save(estimate);

        return estimateMapper.toDTO(estimate);
    }

    @Override
    public void deleteEstimate(Long id) {
        estimateRepository.deleteById(id);
    }
}