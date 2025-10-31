package com.pgAccomodation.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pgAccomodation.entity.Accommodation;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    
    /**
     * Find all available accommodations in a specific city
     * API 1: GET /pg/{city_id}
     */
    List<Accommodation> findByCityIgnoreCaseAndAvailableTrue(String city);
    
    /**
     * Find all available accommodations in a specific locality
     * API 2: GET /pg/{locality}
     */
    List<Accommodation> findByLocalityIgnoreCaseAndAvailableTrue(String locality);
    
    /**
     * Find accommodation by registration number (for uniqueness check)
     */
    Optional<Accommodation> findByRegistrationNumber(String registrationNumber);
    
    /**
     * Find all accommodations by owner ID
     * API 6: GET /owner/places
     */
    List<Accommodation> findByOwnerId(Long ownerId);
    
    /**
     * Find all accommodations by owner ID (ordered by creation date)
     */
    List<Accommodation> findByOwnerIdOrderByCreatedAtDesc(Long ownerId);
    
    /**
     * Check if registration number exists
     */
    boolean existsByRegistrationNumber(String registrationNumber);
    
    /**
     * Find by city (case insensitive)
     */
    List<Accommodation> findByCityIgnoreCase(String city);
    
    /**
     * Find by locality (case insensitive)
     */
    List<Accommodation> findByLocalityIgnoreCase(String locality);
    
    /**
     * Find available places by locality and max rent
     */
    List<Accommodation> findByLocalityIgnoreCaseAndAvailableTrueAndRentLessThanEqual(
        String locality, Double maxRent);
    
    /**
     * Count places by owner
     */
    long countByOwnerId(Long ownerId);
    
    /**
     * Find all available accommodations
     */
    List<Accommodation> findByAvailableTrue();
    
    /**
     * Custom query: Search places by multiple criteria
     */
    @Query("SELECT a FROM Accommodation a WHERE " +
           "(:city IS NULL OR LOWER(a.city) = LOWER(:city)) AND " +
           "(:locality IS NULL OR LOWER(a.locality) = LOWER(:locality)) AND " +
           "(:maxRent IS NULL OR a.rent <= :maxRent) AND " +
           "(:available IS NULL OR a.available = :available)")
    List<Accommodation> searchPlaces(
        @Param("city") String city,
        @Param("locality") String locality,
        @Param("maxRent") Double maxRent,
        @Param("available") Boolean available
    );
}