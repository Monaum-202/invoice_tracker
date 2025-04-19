package com.example.invoice.repository.security;


import com.example.invoice.entity.security.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    boolean existsByUserNameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
    // Fetch all products with pagination and sorting
    Page<Users> findAll(Pageable pageable);


    Page<Users> findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String userName, String firstName, String lastName, String email, Pageable pageable);



    @Query(value = "SELECT u.account_non_expired, u.account_non_locked, u.credentials_non_expired, " +
            "u.email, u.date_created, u.enabled, u.last_updated, u.password, " +
            "u.user_first_name, u.user_last_name, ur.user_name " +
            "FROM users u " +
            "LEFT JOIN userrole ur ON u.user_name = ur.user_name " +
            "WHERE ur.role_name = :roleName", nativeQuery = true)
    List<Object[]> findUsersByRole(@Param("roleName") String roleName);


    Optional<Users> findByUserName(String userName);
}
