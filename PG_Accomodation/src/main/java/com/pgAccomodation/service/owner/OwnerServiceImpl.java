package com.pgAccomodation.service.owner;

import com.pgAccomodation.dto.OwnerDetailsDTO;
import com.pgAccomodation.dto.OwnerRegistrationDTO;
import com.pgAccomodation.entity.Accommodation;
import com.pgAccomodation.entity.Owner;
import com.pgAccomodation.exception.BadRequestException;
import com.pgAccomodation.exception.DuplicateResourceException;
import com.pgAccomodation.exception.ResourceNotFoundException;
import com.pgAccomodation.repository.AccommodationRepository;
import com.pgAccomodation.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    
    private final OwnerRepository ownerRepository;
    private final AccommodationRepository accommodationRepository;
    
    public OwnerServiceImpl(OwnerRepository ownerRepository, 
                           AccommodationRepository accommodationRepository) {
        this.ownerRepository = ownerRepository;
        this.accommodationRepository = accommodationRepository;
    }
    
    @Override
    @Transactional
    public Owner registerOwner(OwnerRegistrationDTO dto) {
        // Validate age >= 18
        if (dto.getAge() < 18) {
            throw new BadRequestException("Owner must be at least 18 years old to register");
        }
        
        // Check if email already exists
        if (ownerRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Owner", "email", dto.getEmail());
        }
        
        // Check if phone already exists
        if (ownerRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicateResourceException("Owner", "phone", dto.getPhone());
        }
        
        // Create owner entity
        Owner owner = new Owner();
        owner.setName(dto.getName());
        owner.setEmail(dto.getEmail());
        owner.setPhone(dto.getPhone());
        owner.setPassword(dto.getPassword()); // In production, hash this!
        owner.setAge(dto.getAge());
        
        return ownerRepository.save(owner);
    }
    
    @Override
    public Owner findOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", id));
    }
    
    @Override
    public OwnerDetailsDTO getOwnerDetails(Long ownerId) {
        Owner owner = findOwnerById(ownerId);
        
        OwnerDetailsDTO dto = new OwnerDetailsDTO();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setEmail(owner.getEmail());
        dto.setPhone(owner.getPhone());
        
        return dto;
    }
    
    @Override
    public List<Accommodation> getOwnerPlaces(Long ownerId) {
        // Verify owner exists
        findOwnerById(ownerId);
        
        return accommodationRepository.findByOwnerIdOrderByCreatedAtDesc(ownerId);
    }
    
    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }
}