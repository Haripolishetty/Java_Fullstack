package com.pgAccomodation.dto;

import jakarta.validation.constraints.*;

public class PlaceDTO {
    
    private Long id;
    
    @NotBlank(message = "Registration number is required")
    private String registrationNumber;
    
    @NotBlank(message = "Place name is required")
    private String placeName;
    
    @NotNull(message = "Built-up area is required")
    @Min(value = 1, message = "Built-up area must be greater than 0")
    private Double builtUpArea;
    
    @NotNull(message = "Rent amount is required")
    @Min(value = 1, message = "Rent must be greater than 0")
    private Double rent;
    
    private String description;
    
    @NotNull(message = "Availability status is required")
    private Boolean available;
    
    private Long visitorCount;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "Locality is required")
    private String locality;
    
    private String address;
    
    @NotNull(message = "Owner ID is required")
    private Long ownerId;
    
    // Constructors
    public PlaceDTO() {}
    
    public PlaceDTO(Long id, String registrationNumber, String placeName, Double builtUpArea, 
                    Double rent, String description, Boolean available, Long visitorCount, 
                    String city, String locality, String address, Long ownerId) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.placeName = placeName;
        this.builtUpArea = builtUpArea;
        this.rent = rent;
        this.description = description;
        this.available = available;
        this.visitorCount = visitorCount;
        this.city = city;
        this.locality = locality;
        this.address = address;
        this.ownerId = ownerId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Double getBuiltUpArea() {
        return builtUpArea;
    }

    public void setBuiltUpArea(Double builtUpArea) {
        this.builtUpArea = builtUpArea;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Long visitorCount) {
        this.visitorCount = visitorCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}