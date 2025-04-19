package com.example.invoice.repository;

import com.example.invoice.entity.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessInfoRepository extends JpaRepository<BusinessInfo, Long> {

    Optional<BusinessInfo> findFirstByCreatedBy_UserName(String userName);

}
