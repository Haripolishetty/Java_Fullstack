package com.pgAccomodation.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "accommodation")
public class Accommodation {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Registration number is required")
    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;
    
    @NotBlank(message = "Place name is required")
    @Column(name = "place_name", nullable = false)
    private String placeName;
    
    @NotNull(message = "Built-up area is required")
    @Min(value = 1, message = "Built-up area must be greater than 0")
    @Column(name = "built_up_area", nullable = false)
    private Double builtUpArea; // in sq ft
    
    @NotNull(message = "Rent amount is required")
    @Min(value = 1, message = "Rent must be greater than 0")
    @Column(name = "rent", nullable = false)
    private Double rent;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @NotNull(message = "Availability status is required")
    @Column(nullable = false)
    private Boolean available = true; // true = available, false = occupied
    
    @Column(name = "visitor_count")
    private Long visitorCount = 0L;
    
    @Column(name = "city", nullable = false)
    private String city;
    
    @Column(name = "locality", nullable = false)
    private String locality;
    
    @Column(name = "address", length = 500)
    private String address;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private Owner owner;
    
    // Constructors
    public Accommodation() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Accommodation(Long id, String registrationNumber, String placeName, Double builtUpArea, 
                         Double rent, String description, Boolean available, Long visitorCount, 
                         String city, String locality, String address, Owner owner) {
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
        this.owner = owner;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Accommodation [id=" + id + ", registrationNumber=" + registrationNumber + 
               ", placeName=" + placeName + ", builtUpArea=" + builtUpArea + ", rent=" + rent + 
               ", description=" + description + ", available=" + available + 
               ", visitorCount=" + visitorCount + ", city=" + city + ", locality=" + locality + 
               ", address=" + address + "]";
    }
}