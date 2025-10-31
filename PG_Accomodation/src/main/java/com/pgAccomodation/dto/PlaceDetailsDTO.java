package com.pgAccomodation.dto;

public class PlaceDetailsDTO {
    
    private Long id;
    private String registrationNumber;
    private String placeName;
    private Double builtUpArea;
    private Double rent;
    private String description;
    private Boolean available;
    private Long visitorCount;
    private String city;
    private String locality;
    private String address;
    private String ownerName;
    
    // Constructors
    public PlaceDetailsDTO() {}
    
    public PlaceDetailsDTO(Long id, String registrationNumber, String placeName, Double builtUpArea,
                           Double rent, String description, Boolean available, Long visitorCount,
                           String city, String locality, String address, String ownerName) {
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
        this.ownerName = ownerName;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}