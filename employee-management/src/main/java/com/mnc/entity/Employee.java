package com.mnc.entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "employees")
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "first_name", nullable = false, length = 50)
   private String firstName;
   @Column(name = "last_name", nullable = false, length = 50)
   private String lastName;
   @Column(nullable = false, unique = true, length = 100)
   private String email;
   @Column(length = 20)
   private String phone;
   @Column(name = "hire_date")
   private LocalDate hireDate;
   @Column(precision = 10, scale = 2)
   private BigDecimal salary;
   @Column(length = 100)
   private String position;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "department_id")
   private Department department;
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "address_id", referencedColumnName = "id")
   private Address address;
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;
   @CreationTimestamp
   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;
   @UpdateTimestamp
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;
   @Column(name = "active")
   private Boolean active = true;
   public Employee() {
   }
   public Employee(String firstName, String lastName, String email) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
   }
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
this.id = id;
   }
   public String getFirstName() {
       return firstName;
   }
   public void setFirstName(String firstName) {
       this.firstName = firstName;
   }
   public String getLastName() {
       return lastName;
   }
   public void setLastName(String lastName) {
       this.lastName = lastName;
   }
   public String getEmail() {
       return email;
   }
   public void setEmail(String email) {
       this.email = email;
   }
   public String getPhone() {
       return phone;
   }
   public void setPhone(String phone) {
       this.phone = phone;
   }
   public LocalDate getHireDate() {
       return hireDate;
   }
   public void setHireDate(LocalDate hireDate) {
       this.hireDate = hireDate;
   }
   public BigDecimal getSalary() {
       return salary;
   }
   public void setSalary(BigDecimal salary) {
       this.salary = salary;
   }
   public String getPosition() {
       return position;
   }
   public void setPosition(String position) {
       this.position = position;
   }
   public Department getDepartment() {
       return department;
   }
   public void setDepartment(Department department) {
       this.department = department;
   }
   public Address getAddress() {
       return address;
   }
   public void setAddress(Address address) {
       this.address = address;
   }
   public User getUser() {
       return user;
   }
   public void setUser(User user) {
       this.user = user;
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
   public Boolean getActive() {
       return active;
   }
   public void setActive(Boolean active) {
       this.active = active;
   }
}