package com.mnc.service;
import com.mnc.entity.Role;
import com.mnc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class RoleService {
   @Autowired
   private RoleRepository roleRepository;
   public List<Role> getAllRoles() {
       return roleRepository.findAll();
   }
   public Optional<Role> getRoleById(Long id) {
       return roleRepository.findById(id);
   }
   public Optional<Role> getRoleByName(String name) {
       return roleRepository.findByName(name);
   }
   public Role createRole(Role role) {
       return roleRepository.save(role);
   }
   public Role updateRole(Long id, Role roleDetails) {
       Role role = roleRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
       role.setName(roleDetails.getName());
       role.setDescription(roleDetails.getDescription());
       return roleRepository.save(role);
   }
   public void deleteRole(Long id) {
       roleRepository.deleteById(id);
   }
   public Boolean existsByName(String name) {
       return roleRepository.existsByName(name);
   }
   public void initializeRoles() {
       if (!existsByName("ROLE_ADMIN")) {
           createRole(new Role("ROLE_ADMIN", "Administrator role"));
       }
       if (!existsByName("ROLE_MANAGER")) {
           createRole(new Role("ROLE_MANAGER", "Manager role"));
       }
       if (!existsByName("ROLE_USER")) {
           createRole(new Role("ROLE_USER", "User role"));
       }
   }
}