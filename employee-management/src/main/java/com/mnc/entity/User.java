package com.mnc.entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false, unique = true, length = 50)
   private String username;
   @Column(nullable = false)
   private String password;
   @Column(nullable = false, unique = true, length = 100)
   private String email;
   @Column(nullable = false)
   private Boolean enabled = true;
   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinTable(
       name = "user_roles",
       joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<Role> roles = new HashSet<>();
   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private Employee employee;
   @CreationTimestamp
   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;
   @UpdateTimestamp
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;
   public User() {
   }
   public User(String username, String password, String email) {
       this.username = username;
       this.password = password;
       this.email = email;
   }
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
this.id = id;
   }
   public String getUsername() {
       return username;
   }
   public void setUsername(String username) {
       this.username = username;
   }
   public String getPassword() {
       return password;
   }
   public void setPassword(String password) {
       this.password = password;
   }
   public String getEmail() {
       return email;
   }
   public void setEmail(String email) {
       this.email = email;
   }
   public Boolean getEnabled() {
       return enabled;
   }
   public void setEnabled(Boolean enabled) {
       this.enabled = enabled;
   }
   public Set<Role> getRoles() {
       return roles;
   }
   public void setRoles(Set<Role> roles) {
       this.roles = roles;
   }
   public Employee getEmployee() {
       return employee;
   }
   public void setEmployee(Employee employee) {
       this.employee = employee;
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
   public void addRole(Role role) {
       this.roles.add(role);
       role.getUsers().add(this);
   }
   public void removeRole(Role role) {
       this.roles.remove(role);
       role.getUsers().remove(this);
   }
}