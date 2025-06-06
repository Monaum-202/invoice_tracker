package com.example.invoice.controller;

import com.example.invoice.dto.ClientDTO;
import com.example.invoice.service.ClientService;
import com.example.invoice.service.InvoiceService;
import com.example.invoice.service.impl.InvoiceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Create a new Client
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    // Get Client by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.getClientById(id);
        return ResponseEntity.ok(clientDTO);
    }

    // Get all Clients with pagination
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> getAllClients(Pageable pageable) {
        Page<ClientDTO> clients = clientService.getAllClients(pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/by-username/{userName}")
    public ResponseEntity<Page<ClientDTO>> getClientsByCreatedBy(
            @PathVariable String userName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(clientService.getClientsByCreatedBy(userName, pageable));
    }



    // Update Client details
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    // Delete Client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public Page<ClientDTO> searchClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            Pageable pageable) {
        return clientService.searchClients(name, email, phone, pageable);
    }

    @GetMapping("/total-amount/{clientId}")
    public ResponseEntity<Double> getTotalAmount(@PathVariable Long clientId) {
        Double total = invoiceService.getTotalAmountByClientId(clientId);
        return ResponseEntity.ok(total != null ? total : 0.0);
    }

    @GetMapping("/total-paid/{clientId}")
    public ResponseEntity<Double> getTotalPaidAmount(@PathVariable Long clientId) {
        Double total = invoiceService.getTotalPaidByClientId(clientId);
        return ResponseEntity.ok(total != null ? total : 0.0);
    }

    @GetMapping("/total-due/{clientId}")
    public ResponseEntity<Double> getTotalDueAmount(@PathVariable Long clientId) {
        Double total = invoiceService.getTotalDueByClientId(clientId);
        return ResponseEntity.ok(total != null ? total : 0.0);
    }


}