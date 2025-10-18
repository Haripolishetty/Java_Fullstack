package com.mnc.entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "roles")
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false, unique = true, length = 50)
   private String name;
   @Column(length = 200)
   private String description;
   @ManyToMany(mappedBy = "roles")
   @JsonIgnore
   private Set<User> users = new HashSet<>();
   public Role() {
   }
   public Role(String name) {
       this.name = name;
   }
   public Role(String name, String description) {
       this.name = name;
       this.description = description;
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
   public Set<User> getUsers() {
       return users;
   }
   public void setUsers(Set<User> users) {
       this.users = users;
   }
}