//package com.example.invoice.service;
//
//import com.example.invoice.dto.TaxDTO;
//import com.example.invoice.entity.Tax;
//import com.example.invoice.repository.TaxRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class TaxService {
//
//    private final TaxRepository taxRepository;
//
//    public TaxDTO addTax(TaxDTO taxDTO) {
//        Tax tax = new Tax();
//        BeanUtils.copyProperties(taxDTO, tax);
//        tax = taxRepository.save(tax);
//        return new TaxDTO(tax.getId(), tax.getTaxType(), tax.getTaxPercentage());
//    }
//
//    public List<TaxDTO> getAllTaxes() {
//        return taxRepository.findAll().stream()
//                .map(tax -> new TaxDTO(tax.getId(), tax.getTaxType(), tax.getTaxPercentage()))
//                .collect(Collectors.toList());
//    }
//}
