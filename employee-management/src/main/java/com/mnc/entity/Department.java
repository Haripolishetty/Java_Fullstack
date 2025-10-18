package com.mnc.entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "departments")
public class Department {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false, unique = true, length = 100)
   private String name;
   @Column(length = 500)
   private String description;
   @Column(length = 100)
   private String location;
   @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnore
   private List<Employee> employees = new ArrayList<>();
   @CreationTimestamp
   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;
   @UpdateTimestamp
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;
   @Column(name = "active")
   private Boolean active = true;
   public Department() {
   }
   public Department(String name, String location) {
       this.name = name;
       this.location = location;
   }
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
this.id = id;
   }
   public String getName() {
       return name;
   }
   public void setName(String name) {
       this.name = name;
   }
   public String getDescription() {
       return description;
   }
   public void setDescription(String description) {
       this.description = description;
   }
   public String getLocation() {
       return location;
   }
   public void setLocation(String location) {
       this.location = location;
   }
   public List<Employee> getEmployees() {
       return employees;
   }
   public void setEmployees(List<Employee> employees) {
       this.employees = employees;
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
   public void addEmployee(Employee employee) {
       employees.add(employee);
       employee.setDepartment(this);
   }
   public void removeEmployee(Employee employee) {
       employees.remove(employee);
       employee.setDepartment(null);
   }
}