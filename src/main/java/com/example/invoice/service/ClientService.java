package com.example.invoice.service;

import com.example.invoice.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    Page<ClientDTO> getAllClients(Pageable pageable);
    ClientDTO getClientById(Long id);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);

    Page<ClientDTO> searchClients(String name, String email, String phone, Pageable pageable);
}

