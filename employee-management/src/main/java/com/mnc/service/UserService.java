//package com.mnc.service;
//import com.mnc.entity.User;
//import com.mnc.entity.Role;
//import com.mnc.repository.UserRepository;
//import com.mnc.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//import java.util.Optional;
//@Service
//@Transactional
//public class UserService {
//   @Autowired
//   private UserRepository userRepository;
//   @Autowired
//   private RoleRepository roleRepository;
//   
//   private PasswordEncoder passwordEncoder;
//   public List<User> getAllUsers() {
//       return userRepository.findAll();
//   }
//   public Optional<User> getUserById(Long id) {
//       return userRepository.findById(id); 
//   }
//   public Optional<User> getUserByUsername(String username) {
//       return userRepository.findByUsername(username);
//   }
//   public Optional<User> getUserByEmail(String email) {
//       return userRepository.findByEmail(email);
//   }
//   public User createUser(User user) {
//       user.setPassword(passwordEncoder.encode(user.getPassword()));
//       return userRepository.save(user);
//   }
//   public User updateUser(Long id, User userDetails) {
//       User user = userRepository.findById(id)
//               .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//       user.setUsername(userDetails.getUsername());
//       user.setEmail(userDetails.getEmail());
//       user.setEnabled(userDetails.getEnabled());
//       if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
//           user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
//       }
//       return userRepository.save(user);
//   }
//   public void deleteUser(Long id) {
//       userRepository.deleteById(id);
//   }
//   public void assignRoleToUser(Long userId, Long roleId) {
//       User user = userRepository.findById(userId)
//               .orElseThrow(() -> new RuntimeException("User not found"));
//       Role role = roleRepository.findById(roleId)
//               .orElseThrow(() -> new RuntimeException("Role not found"));
//       user.addRole(role);
//       userRepository.save(user);
//   }
//   public void removeRoleFromUser(Long userId, Long roleId) {
//       User user = userRepository.findById(userId)
//               .orElseThrow(() -> new RuntimeException("User not found"));
//       Role role = roleRepository.findById(roleId)
//               .orElseThrow(() -> new RuntimeException("Role not found"));
//       user.removeRole(role);
//       userRepository.save(user);
//   }
//   public Boolean existsByUsername(String username) {
//       return userRepository.existsByUsername(username);
//   }
//   public Boolean existsByEmail(String email) {
//       return userRepository.existsByEmail(email);
//   }
//}

package com.mnc.service;

import com.mnc.entity.User;
import com.mnc.entity.Role;
import com.mnc.repository.UserRepository;
import com.mnc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   public List<User> getAllUsers() {
       return userRepository.findAll();
   }
   public Optional<User> getUserById(Long id) {
       return userRepository.findById(id);
   }
   public Optional<User> getUserByUsername(String username) {
       return userRepository.findByUsername(username);
   }
   public Optional<User> getUserByEmail(String email) {
       return userRepository.findByEmail(email);
   }
   public User createUser(User user) {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
   }
   public User updateUser(Long id, User userDetails) {
       User user = userRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
       user.setUsername(userDetails.getUsername());
       user.setEmail(userDetails.getEmail());
       user.setEnabled(userDetails.getEnabled());
       if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
           user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
       }
       return userRepository.save(user);
   }
   public void deleteUser(Long id) {
       userRepository.deleteById(id);
   }
   public void assignRoleToUser(Long userId, Long roleId) {
       User user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("User not found"));
       Role role = roleRepository.findById(roleId)
               .orElseThrow(() -> new RuntimeException("Role not found"));
       user.addRole(role);
       userRepository.save(user);
   }
   public void removeRoleFromUser(Long userId, Long roleId) {
       User user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("User not found"));
       Role role = roleRepository.findById(roleId)
               .orElseThrow(() -> new RuntimeException("Role not found"));
       user.removeRole(role);
       userRepository.save(user);
   }
   public Boolean existsByUsername(String username) {
       return userRepository.existsByUsername(username);
   }
   public Boolean existsByEmail(String email) {
       return userRepository.existsByEmail(email);
   }
}
