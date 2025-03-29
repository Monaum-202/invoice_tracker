package com.example.invoice.service.impl;

import com.example.invoice.dto.ClientDTO;
import com.example.invoice.entity.Client;
import com.example.invoice.mapper.ClientMapper;
import com.example.invoice.repository.ClientRepository;
import com.example.invoice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper; // Inject the clientMapper for conversion between DTO and Entity

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        // Convert the ClientDTO to Client entity using the mapper
        Client client = clientMapper.toEntity(clientDTO);
        // Save the Client entity to the repository
        client = clientRepository.save(client);
        // Convert the saved entity back to ClientDTO and return it
        return clientMapper.toDTO(client);
    }

    @Override
    public Page<ClientDTO> getAllClients(Pageable pageable) {
        // Retrieve a paginated list of clients and convert each entity to a DTO
        return clientRepository.findAll(pageable)
                .map(clientMapper::toDTO);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        // Find the client by its ID and convert it to ClientDTO
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return clientMapper.toDTO(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        // Find the client to update
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Update the entity with new information from the DTO
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());

        // Save the updated client and return the updated DTO
        client = clientRepository.save(client);
        return clientMapper.toDTO(client);
    }

    @Override
    public void deleteClient(Long id) {
        // Delete the client by ID
        clientRepository.deleteById(id);
    }

    @Override
    public Page<ClientDTO> searchClients(String name, String email, String phone, Pageable pageable) {
        Page<Client> clients = clientRepository.searchClients(name, email, phone, pageable);
        return clients.map(clientMapper::toDTO);
    }
}