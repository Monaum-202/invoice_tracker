package com.example.invoice.repository;

import com.example.invoice.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Page<Client> findAll(Pageable pageable);

    Client findByPhone(String phone);

    @Query("SELECT c FROM Client c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) " +
            "AND (:email IS NULL OR c.email LIKE %:email%) " +
            "AND (:phone IS NULL OR c.phone LIKE %:phone%)")
    Page<Client> searchClients(
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            Pageable pageable);

    Page<Client> findAllByCreatedBy_UserName(String userName, Pageable pageable);
}

