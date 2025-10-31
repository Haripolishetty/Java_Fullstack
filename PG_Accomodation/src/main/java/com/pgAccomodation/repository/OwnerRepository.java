package com.pgAccomodation.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pgAccomodation.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    
    /**
     * Find owner by email
     */
    Optional<Owner> findByEmail(String email);
    
    /**
     * Find owner by phone
     */
    Optional<Owner> findByPhone(String phone);
    
    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Check if phone exists
     */
    boolean existsByPhone(String phone);
}