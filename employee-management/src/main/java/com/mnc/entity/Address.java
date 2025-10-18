package com.mnc.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "addresses")
public class Address {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(length = 200)
   private String street;
   @Column(length = 100)
   private String city;
   @Column(length = 100)
   private String state;
   @Column(name = "zip_code", length = 20)
   private String zipCode;
   @Column(length = 100)
   private String country;
   @OneToOne(mappedBy = "address")
   private Employee employee;
   public Address() {
   }
   public Address(String street, String city, String state, String zipCode, String country) {
       this.street = street;
       this.city = city;
       this.state = state;
       this.zipCode = zipCode;
       this.country = country;
   }
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
this.id = id;
   }
   public String getStreet() {
       return street;
   }
   public void setStreet(String street) {
       this.street = street;
   }
   public String getCity() {
       return city;
   }
   public void setCity(String city) {
       this.city = city;
   }
   public String getState() {
       return state;
   }
   public void setState(String state) {
       this.state = state;
   }
   public String getZipCode() {
       return zipCode;
   }
   public void setZipCode(String zipCode) {
       this.zipCode = zipCode;
   }
   public String getCountry() {
       return country;
   }
   public void setCountry(String country) {
       this.country = country;
   }
   public Employee getEmployee() {
       return employee;
   }
   public void setEmployee(Employee employee) {
       this.employee = employee;
   }
}