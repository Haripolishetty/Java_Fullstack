package com.pgAccomodation.service.accommodation;

import com.pgAccomodation.dto.OwnerDetailsDTO;
import com.pgAccomodation.dto.PlaceDTO;
import com.pgAccomodation.dto.PlaceDetailsDTO;
import com.pgAccomodation.entity.Accommodation;

import java.util.List;

public interface AccommodationService {
    
    /**
     * API 1: Get all available PG places in a specific city
     */
    List<Accommodation> getPlacesByCity(String city);
    
    /**
     * API 2: Get all available PG places in a specific locality
     */
    List<Accommodation> getPlacesByLocality(String locality);
    
    /**
     * API 3: Get specific place details by ID (increments visitor count)
     */
    PlaceDetailsDTO getPlaceDetails(Long id);
    
    /**
     * API 4: Get owner details for a specific place
     * Only returns details if place is available
     */
    OwnerDetailsDTO getOwnerDetailsByPlace(Long placeId);
    
    /**
     * API 5: Add a new place (Owner only)
     */
    Accommodation addPlace(PlaceDTO dto);
    
    /**
     * API 6: Get all places added by owner
     */
    List<Accommodation> getPlacesByOwner(Long ownerId);
    
    /**
     * API 7: Change availability status
     */
    Accommodation changeAvailability(Long id, Boolean available);
    
    /**
     * API 8: Edit place details
     */
    Accommodation editPlace(Long id, PlaceDTO dto);
    
    /**
     * API 9: Delete place
     */
    void deletePlace(Long id);
    
    /**
     * Get all accommodations
     */
    List<Accommodation> findAll();
    
    /**
     * Find by ID
     */
    Accommodation findById(Long id);
}