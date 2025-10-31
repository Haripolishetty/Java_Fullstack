package com.pgAccomodation.controller;

import com.pgAccomodation.dto.ApiResponse;
import com.pgAccomodation.dto.AvailabilityStatusDTO;
import com.pgAccomodation.dto.OwnerRegistrationDTO;
import com.pgAccomodation.dto.PlaceDTO;
import com.pgAccomodation.entity.Accommodation;
import com.pgAccomodation.entity.Owner;
import com.pgAccomodation.service.accommodation.AccommodationService;
import com.pgAccomodation.service.owner.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@Tag(name = "Owner", description = "Owner Management APIs")
public class OwnerController {
    
    private final OwnerService ownerService;
    private final AccommodationService accommodationService;
    
    public OwnerController(OwnerService ownerService, 
                          AccommodationService accommodationService) {
        this.ownerService = ownerService;
        this.accommodationService = accommodationService;
    }
    
    /**
     * Register a new owner
     * POST /owner/register
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new owner", 
               description = "Register a new owner. Age must be at least 18 years.")
    public ResponseEntity<ApiResponse> registerOwner(@Valid @RequestBody OwnerRegistrationDTO dto) {
        Owner owner = ownerService.registerOwner(dto);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Owner registered successfully", 
            owner
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * API 5: Add a new PG place
     * POST /owner/places/add
     */
    @PostMapping("/places/add")
    @Operation(summary = "Add a new PG place", 
               description = "Owner can add a new PG accommodation place")
    public ResponseEntity<ApiResponse> addPlace(@Valid @RequestBody PlaceDTO dto) {
        Accommodation accommodation = accommodationService.addPlace(dto);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Place added successfully", 
            accommodation
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * API 6: Get all places added by owner
     * GET /owner/places?ownerId={ownerId}
     */
    @GetMapping("/places")
    @Operation(summary = "Get all places added by owner", 
               description = "Retrieve all PG places added by a specific owner")
    public ResponseEntity<ApiResponse> getOwnerPlaces(@RequestParam Long ownerId) {
        List<Accommodation> places = accommodationService.getPlacesByOwner(ownerId);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Places retrieved successfully", 
            places
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * API 7: Change availability status
     * PUT /owner/places/{id}
     */
    @PutMapping("/places/{id}")
    @Operation(summary = "Change place availability status", 
               description = "Change the availability status of a PG place (Available/Occupied)")
    public ResponseEntity<ApiResponse> changeAvailability(
            @PathVariable Long id,
            @Valid @RequestBody AvailabilityStatusDTO dto) {
        
        Accommodation accommodation = accommodationService.changeAvailability(id, dto.getAvailable());
        
        String status = dto.getAvailable() ? "Available" : "Occupied";
        ApiResponse response = new ApiResponse(
            true, 
            "Place status changed to " + status + " successfully", 
            accommodation
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * API 8: Edit place details
     * PUT /owner/places/edit
     */
    @PutMapping("/places/edit")
    @Operation(summary = "Edit place details", 
               description = "Owner can edit details of their PG place")
    public ResponseEntity<ApiResponse> editPlace(
            @RequestParam Long id,
            @Valid @RequestBody PlaceDTO dto) {
        
        Accommodation accommodation = accommodationService.editPlace(id, dto);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Place details updated successfully", 
            accommodation
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * API 9: Delete place
     * DELETE /owner/places/delete?id={id}
     */
    @DeleteMapping("/places/delete")
    @Operation(summary = "Delete place", 
               description = "Owner can delete their PG place")
    public ResponseEntity<ApiResponse> deletePlace(@RequestParam Long id) {
        accommodationService.deletePlace(id);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Place deleted successfully"
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get owner by ID
     * GET /owner/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get owner by ID")
    public ResponseEntity<ApiResponse> getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.findOwnerById(id);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Owner retrieved successfully", 
            owner
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get all owners
     * GET /owner/all
     */
    @GetMapping("/all")
    @Operation(summary = "Get all owners")
    public ResponseEntity<ApiResponse> getAllOwners() {
        ApiResponse response = new ApiResponse(
            true, 
            "Owners retrieved successfully", 
            ownerService.getAllOwners()
        );
        
        return ResponseEntity.ok(response);
    }
}