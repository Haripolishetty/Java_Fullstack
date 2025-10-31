package com.pgAccomodation.service.accommodation;

import com.pgAccomodation.dto.OwnerDetailsDTO;
import com.pgAccomodation.dto.PlaceDTO;
import com.pgAccomodation.dto.PlaceDetailsDTO;
import com.pgAccomodation.entity.Accommodation;
import com.pgAccomodation.entity.Owner;
import com.pgAccomodation.exception.BadRequestException;
import com.pgAccomodation.exception.DuplicateResourceException;
import com.pgAccomodation.exception.ResourceNotFoundException;
import com.pgAccomodation.exception.UnauthorizedException;
import com.pgAccomodation.repository.AccommodationRepository;
import com.pgAccomodation.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    
    private final AccommodationRepository accommodationRepository;
    private final OwnerRepository ownerRepository;
    
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                   OwnerRepository ownerRepository) {
        this.accommodationRepository = accommodationRepository;
        this.ownerRepository = ownerRepository;
    }
    
    @Override
    public List<Accommodation> getPlacesByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new BadRequestException("City name cannot be empty");
        }
        return accommodationRepository.findByCityIgnoreCaseAndAvailableTrue(city);
    }
    
    @Override
    public List<Accommodation> getPlacesByLocality(String locality) {
        if (locality == null || locality.trim().isEmpty()) {
            throw new BadRequestException("Locality name cannot be empty");
        }
        return accommodationRepository.findByLocalityIgnoreCaseAndAvailableTrue(locality);
    }
    
    @Override
    @Transactional
    public PlaceDetailsDTO getPlaceDetails(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", id));
        
        // Increment visitor count
        accommodation.setVisitorCount(accommodation.getVisitorCount() + 1);
        accommodationRepository.save(accommodation);
        
        // Convert to DTO
        PlaceDetailsDTO dto = new PlaceDetailsDTO();
        dto.setId(accommodation.getId());
        dto.setRegistrationNumber(accommodation.getRegistrationNumber());
        dto.setPlaceName(accommodation.getPlaceName());
        dto.setBuiltUpArea(accommodation.getBuiltUpArea());
        dto.setRent(accommodation.getRent());
        dto.setDescription(accommodation.getDescription());
        dto.setAvailable(accommodation.getAvailable());
        dto.setVisitorCount(accommodation.getVisitorCount());
        dto.setCity(accommodation.getCity());
        dto.setLocality(accommodation.getLocality());
        dto.setAddress(accommodation.getAddress());
        dto.setOwnerName(accommodation.getOwner().getName());
        
        return dto;
    }
    
    @Override
    public OwnerDetailsDTO getOwnerDetailsByPlace(Long placeId) {
        Accommodation accommodation = accommodationRepository.findById(placeId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", placeId));
        
        // Check if place is available
        if (!accommodation.getAvailable()) {
            throw new UnauthorizedException(
                "Owner contact details are not available for occupied places. " +
                "You can only view place details."
            );
        }
        
        // Return owner details
        Owner owner = accommodation.getOwner();
        OwnerDetailsDTO dto = new OwnerDetailsDTO();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setEmail(owner.getEmail());
        dto.setPhone(owner.getPhone());
        
        return dto;
    }
    
    @Override
    @Transactional
    public Accommodation addPlace(PlaceDTO dto) {
        // Validate owner exists and age >= 18
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", dto.getOwnerId()));
        
        if (owner.getAge() < 18) {
            throw new BadRequestException("Owner must be at least 18 years old to post accommodation");
        }
        
        // Check if registration number already exists
        if (accommodationRepository.existsByRegistrationNumber(dto.getRegistrationNumber())) {
            throw new DuplicateResourceException(
                "Accommodation", "registration number", dto.getRegistrationNumber()
            );
        }
        
        // Create accommodation entity
        Accommodation accommodation = new Accommodation();
        accommodation.setRegistrationNumber(dto.getRegistrationNumber());
        accommodation.setPlaceName(dto.getPlaceName());
        accommodation.setBuiltUpArea(dto.getBuiltUpArea());
        accommodation.setRent(dto.getRent());
        accommodation.setDescription(dto.getDescription());
        accommodation.setAvailable(dto.getAvailable() != null ? dto.getAvailable() : true);
        accommodation.setVisitorCount(0L);
        accommodation.setCity(dto.getCity());
        accommodation.setLocality(dto.getLocality());
        accommodation.setAddress(dto.getAddress());
        accommodation.setOwner(owner);
        
        return accommodationRepository.save(accommodation);
    }
    
    @Override
    public List<Accommodation> getPlacesByOwner(Long ownerId) {
        // Verify owner exists
        ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", ownerId));
        
        return accommodationRepository.findByOwnerIdOrderByCreatedAtDesc(ownerId);
    }
    
    @Override
    @Transactional
    public Accommodation changeAvailability(Long id, Boolean available) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", id));
        
        accommodation.setAvailable(available);
        return accommodationRepository.save(accommodation);
    }
    
    @Override
    @Transactional
    public Accommodation editPlace(Long id, PlaceDTO dto) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", id));
        
        // Check if registration number is being changed and if it's unique
        if (!accommodation.getRegistrationNumber().equals(dto.getRegistrationNumber())) {
            if (accommodationRepository.existsByRegistrationNumber(dto.getRegistrationNumber())) {
                throw new DuplicateResourceException(
                    "Accommodation", "registration number", dto.getRegistrationNumber()
                );
            }
            accommodation.setRegistrationNumber(dto.getRegistrationNumber());
        }
        
        // Update fields
        accommodation.setPlaceName(dto.getPlaceName());
        accommodation.setBuiltUpArea(dto.getBuiltUpArea());
        accommodation.setRent(dto.getRent());
        accommodation.setDescription(dto.getDescription());
        accommodation.setCity(dto.getCity());
        accommodation.setLocality(dto.getLocality());
        accommodation.setAddress(dto.getAddress());
        
        if (dto.getAvailable() != null) {
            accommodation.setAvailable(dto.getAvailable());
        }
        
        return accommodationRepository.save(accommodation);
    }
    
    @Override
    @Transactional
    public void deletePlace(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", id));
        
        accommodationRepository.delete(accommodation);
    }
    
    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }
    
    @Override
    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", id));
    }
}