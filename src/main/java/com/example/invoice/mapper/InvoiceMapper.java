package com.example.invoice.mapper;

import com.example.invoice.dto.BusinessInfoDTO;
import com.example.invoice.dto.ClientDTO;
import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.dto.InvoiceItemDTO;
import com.example.invoice.entity.*;
import com.example.invoice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {
    @Autowired
    private ClientRepository clientRepository;
    private LocalDate dueDate;

    public InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        return new InvoiceDTO(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                invoice.getIssueDate(),
                invoice.getSubtotal(),
                invoice.getTotalAmount(),
                invoice.getDiscountPersentage(),
                invoice.getDiscountCash(),
                invoice.getPaidAmount(),
                invoice.getDueAmount(),
                invoice.getDueDate(),
                invoice.getStatus(),
                toDTO(invoice.getClient()),
                invoice.getCreatedBy().getId(),
                invoice.getItems().stream()
                        .map(item -> new InvoiceItemDTO(
                                item.getId(),
                                item.getItemName(),
                                item.getQuantity(),
                                item.getUnitPrice(),
                                item.getTotalPrice(),
                                invoice.getId() // Pass invoice ID to the DTO
                        ))
                        .collect(Collectors.toList()),
                toDTO(invoice.getBusinessInfo()) // Assuming you have a method to map BusinessInfo to BusinessInfoDTO
        );
    }

    // Convert InvoiceDTO back to Invoice Entity
    public Invoice toEntity(InvoiceDTO invoiceDTO) {
        if (invoiceDTO == null) {
            return null;
        }

        Invoice invoice = new Invoice();
        invoice.setId(invoiceDTO.getId());
        invoice.setInvoiceNumber(invoiceDTO.getInvoiceNumber());
        invoice.setIssueDate(invoiceDTO.getIssueDate());
        invoice.setSubtotal(invoiceDTO.getSubtotal());
        invoice.setDiscountPersentage(invoiceDTO.getDiscountPersentage());
        invoice.setDiscountCash(invoiceDTO.getDiscountCash());
        invoice.setDueDate(dueDate);
        invoice.setStatus(invoiceDTO.getStatus());
        invoice.setPaidAmount(invoiceDTO.getPaidAmount());

        // Set the client entity (assuming you have a method to get a Client by ID)
        ClientDTO clientDTO = invoiceDTO.getClient();
        if (clientDTO != null) {
            Client existingClient = clientRepository.findByNid(clientDTO.getNid());
            if (existingClient != null) {
                // Use existing client if NID matches
                invoice.setClient(existingClient);
            } else {
                // Create new client if NID does not exist
                Client client = toEntity(clientDTO);
                invoice.setClient(client);
            }
        }
        User user = new User();
        user.setId(invoiceDTO.getCreatedBy());
        invoice.setCreatedBy(user);

        // Set BusinessInfo (assuming you have a method to convert BusinessInfoDTO to BusinessInfo)
        BusinessInfo businessInfo = toEntity(invoiceDTO.getBusinessInfo());
        invoice.setBusinessInfo(businessInfo);

        // Set Invoice Items (using the constructor with parameters)
        List<InvoiceItem> items = invoiceDTO.getItems().stream()
                .map(itemDTO -> new InvoiceItem(
                        itemDTO.getId(),
                        itemDTO.getItemName(),
                        itemDTO.getQuantity(),
                        itemDTO.getUnitPrice(),
                        itemDTO.getTotalPrice(),
                        invoice // Pass the invoice object to link items to the invoice
                ))
                .collect(Collectors.toList());
        invoice.setItems(items);
        invoice.calculateTotalAmount();
        invoice.calculateDueAmount();
        return invoice;
    }





    public BusinessInfoDTO toDTO(BusinessInfo businessInfo) {
        if (businessInfo == null) {
            return null;
        }

        return new BusinessInfoDTO(
                businessInfo.getId(),
                businessInfo.getBusinessName(),
                businessInfo.getAddress(),
                businessInfo.getPhone(),
                businessInfo.getEmail(),
                businessInfo.getTaxId(),
                businessInfo.getWebsite()
        );
    }


    // Convert BusinessInfoDTO to BusinessInfo Entity
    public BusinessInfo toEntity(BusinessInfoDTO businessInfoDTO) {
        if (businessInfoDTO == null) {
            return null;
        }

        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setId(businessInfoDTO.getId());
        businessInfo.setBusinessName(businessInfoDTO.getBusinessName());
        businessInfo.setAddress(businessInfoDTO.getAddress());
        businessInfo.setPhone(businessInfoDTO.getPhone());
        businessInfo.setEmail(businessInfoDTO.getEmail());
        businessInfo.setTaxId(businessInfoDTO.getTaxId());
        businessInfo.setWebsite(businessInfoDTO.getWebsite());

        return businessInfo;
    }




    //Client Mapper
    public ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setNid(client.getNid());
        clientDTO.setAddress(client.getAddress());
        return clientDTO;
    }

    // Convert ClientDTO to Client Entity
    public Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setNid(clientDTO.getNid());
        client.setAddress(clientDTO.getAddress());
        return client;
    }
}
