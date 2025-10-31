package com.pgAccomodation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pgAccomodation.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    /**
     * Find all bookings by tenant
     */
    List<Booking> findByTenantId(Long tenantId);
    
    /**
     * Find all bookings by accommodation
     */
    List<Booking> findByAccommodationId(Long accommodationId);
    
    /**
     * Find bookings by status
     */
    List<Booking> findByStatus(String status);
    
    /**
     * Find bookings by tenant and status
     */
    List<Booking> findByTenantIdAndStatus(Long tenantId, String status);
    
    /**
     * Count bookings by accommodation
     */
    long countByAccommodationId(Long accommodationId);
}