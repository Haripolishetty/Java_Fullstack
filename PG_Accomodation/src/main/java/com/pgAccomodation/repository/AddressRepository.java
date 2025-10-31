package com.pgAccomodation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pgAccomodation.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    /**
     * Find addresses by city
     */
    List<Address> findByCityIgnoreCase(String city);
    
    /**
     * Find addresses by locality
     */
    List<Address> findByLocalityIgnoreCase(String locality);
    
    /**
     * Find addresses by city and locality
     */
    List<Address> findByCityIgnoreCaseAndLocalityIgnoreCase(String city, String locality);
}