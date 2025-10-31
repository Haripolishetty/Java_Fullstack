package com.pgAccomodation.dto;

import jakarta.validation.constraints.NotNull;

public class AvailabilityStatusDTO {
    
    @NotNull(message = "Availability status is required")
    private Boolean available;
    
    // Constructors
    public AvailabilityStatusDTO() {}
    
    public AvailabilityStatusDTO(Boolean available) {
        this.available = available;
    }
    
    // Getters and Setters
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}