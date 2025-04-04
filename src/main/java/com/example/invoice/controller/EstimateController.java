package com.example.invoice.controller;

import com.example.invoice.dto.EstimateDTO;
import com.example.invoice.service.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estimates")
@CrossOrigin(origins = "*")
public class EstimateController {

    @Autowired
    private EstimateService estimateService;

    @PostMapping
    public ResponseEntity<EstimateDTO> createEstimate(@RequestBody EstimateDTO estimateDTO) {
        return ResponseEntity.ok(estimateService.createEstimate(estimateDTO));
    }


    @GetMapping
    public ResponseEntity<Page<EstimateDTO>> getAllEstimates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return ResponseEntity.ok(estimateService.getAllEstimates(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstimateDTO> getEstimateById(@PathVariable Long id) {
        return ResponseEntity.ok(estimateService.getEstimateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstimateDTO> updateEstimate(@PathVariable Long id, @RequestBody EstimateDTO estimateDTO) {
        return ResponseEntity.ok(estimateService.updateEstimate(id, estimateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstimate(@PathVariable Long id) {
        estimateService.deleteEstimate(id);
        return ResponseEntity.noContent().build();
    }
}