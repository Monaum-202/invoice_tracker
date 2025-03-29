package com.example.invoice.mapper;

import com.example.invoice.dto.ClientDTO;
import com.example.invoice.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    // Convert Client Entity to ClientDTO
    public ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
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
        client.setAddress(clientDTO.getAddress());
        return client;
    }

    // Update existing Client Entity with ClientDTO data
    public void updateEntityFromDTO(ClientDTO clientDTO, Client client) {
        if (clientDTO == null || client == null) {
            return;
        }

        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
    }
}