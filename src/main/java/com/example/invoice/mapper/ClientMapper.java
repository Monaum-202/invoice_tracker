package com.example.invoice.mapper;

import com.example.invoice.dto.ClientDTO;
import com.example.invoice.entity.Client;
import com.example.invoice.entity.security.Users;
import com.example.invoice.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    @Autowired
    private UserRepository userRepository;

    public ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setNid(client.getNid());
        dto.setAddress(client.getAddress());
        dto.setCreatedBy(client.getCreatedBy().getUserName());
        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setNid(dto.getNid());
        client.setAddress(dto.getAddress());
        if (dto.getCreatedBy() != null) {
            Users user = userRepository.findByUserName(dto.getCreatedBy()).orElseThrow(() -> new RuntimeException("User not found: " + dto.getCreatedBy()));
            client.setCreatedBy(user);
        }
        return client;
    }
}
