package com.pgAccomodation.controller;

import com.pgAccomodation.dto.ApiResponse;
import com.pgAccomodation.dto.OwnerDetailsDTO;
import com.pgAccomodation.dto.PlaceDetailsDTO;
import com.pgAccomodation.entity.Accommodation;
import com.pgAccomodation.service.accommodation.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg")
@Tag(name = "PG Accommodation", description = "PG Accommodation Search APIs")
public class AccommodationController {
    
    private final AccommodationService accommodationService;
    
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }
    
    /**
     * API 1: Retrieve available PG places in a specific city
     * GET /pg/{city}
     */
    @GetMapping("/{city}")
    @Operation(summary = "Get available PG places by city", 
               description = "Retrieve all available PG accommodations in a specific city")
    public ResponseEntity<ApiResponse> getPlacesByCity(@PathVariable String city) {
        List<Accommodation> places = accommodationService.getPlacesByCity(city);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Available places in " + city + " retrieved successfully", 
            places
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * API 2: List out available PG places in a specific locality
     * GET /pg/locality/{locality}
     */
    @GetMapping("/locality/{locality}")
    @Operation(summary = "Get available PG places by locality", 
               description = "Retrieve all available PG accommodations in a specific locality")
    public ResponseEntity<ApiResponse> getPlacesByLocality(@PathVariable String locality) {
        List<Accommodation> places = accommodationService.getPlacesByLocality(locality);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Available places in " + locality + " retrieved successfully", 
            places
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * API 3: Retrieve a specific PG place detail by ID
     * GET /pg/details/{id}
     */
    @GetMapping("/details/{id}")
    @Operation(summary = "Get PG place details by ID", 
               description = "Retrieve detailed information about a specific PG place. Increments visitor count.")
    public ResponseEntity<ApiResponse> getPlaceDetails(@PathVariable Long id) {
        PlaceDetailsDTO placeDetails = accommodationService.getPlaceDetails(id);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Place details retrieved successfully", 
            placeDetails
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * API 4: Retrieve the owner details of a specific PG
     * GET /pg/owner/{id}
     */
    @GetMapping("/owner/{id}")
    @Operation(summary = "Get owner details by place ID", 
               description = "Retrieve owner contact details for a PG place. Only available if place status is 'Available'.")
    public ResponseEntity<ApiResponse> getOwnerDetails(@PathVariable Long id) {
        OwnerDetailsDTO ownerDetails = accommodationService.getOwnerDetailsByPlace(id);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Owner details retrieved successfully", 
            ownerDetails
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get all accommodations (for testing purposes)
     * GET /pg/all
     */
    @GetMapping("/all")
    @Operation(summary = "Get all PG places", 
               description = "Retrieve all PG accommodations (including occupied)")
    public ResponseEntity<ApiResponse> getAllPlaces() {
        List<Accommodation> places = accommodationService.findAll();
        
        ApiResponse response = new ApiResponse(
            true, 
            "All places retrieved successfully", 
            places
        );
        
        return ResponseEntity.ok(response);
    }
}