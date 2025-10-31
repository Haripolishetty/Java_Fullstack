package com.pgAccomodation.service.owner;

import com.pgAccomodation.dto.OwnerDetailsDTO;
import com.pgAccomodation.dto.OwnerRegistrationDTO;
import com.pgAccomodation.entity.Accommodation;
import com.pgAccomodation.entity.Owner;

import java.util.List;

public interface OwnerService {
    
    /**
     * Register a new owner
     * Validates age >= 18 and unique email/phone
     */
    Owner registerOwner(OwnerRegistrationDTO dto);
    
    /**
     * Find owner by ID
     */
    Owner findOwnerById(Long id);
    
    /**
     * Get owner details (for public display)
     */
    OwnerDetailsDTO getOwnerDetails(Long ownerId);
    
    /**
     * Get all places owned by an owner
     */
    List<Accommodation> getOwnerPlaces(Long ownerId);
    
    /**
     * Get all owners
     */
    List<Owner> getAllOwners();
}