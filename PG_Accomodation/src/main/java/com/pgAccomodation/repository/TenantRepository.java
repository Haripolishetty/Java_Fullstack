package com.pgAccomodation.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pgAccomodation.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    
    /**
     * Find tenant by email
     */
    Optional<Tenant> findByEmail(String email);
    
    /**
     * Find tenant by phone
     */
    Optional<Tenant> findByPhone(String phone);
    
    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Check if phone exists
     */
    boolean existsByPhone(String phone);
}