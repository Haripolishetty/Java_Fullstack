package com.mnc.controller;
import com.mnc.entity.User;
import com.mnc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
   @Autowired
   private UserService userService;
   @GetMapping
   public ResponseEntity<List<User>> getAllUsers() {
       return ResponseEntity.ok(userService.getAllUsers());
   }
   @GetMapping("/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
       return userService.getUserById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/username/{username}")
   public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
       return userService.getUserByUsername(username)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @PostMapping
   public ResponseEntity<User> createUser(@RequestBody User user) {
       try {
           if (userService.existsByUsername(user.getUsername())) {
               return ResponseEntity.badRequest().build();
           }
           User createdUser = userService.createUser(user);
           return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @PutMapping("/{id}")
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
       try {
           User updatedUser = userService.updateUser(id, user);
           return ResponseEntity.ok(updatedUser);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
       try {
           userService.deleteUser(id);
           Map<String, String> response = new HashMap<>();
           response.put("message", "User deleted successfully");
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @PostMapping("/{userId}/roles/{roleId}")
   public ResponseEntity<Map<String, String>> assignRole(
           @PathVariable Long userId,
           @PathVariable Long roleId) {
       try {
           userService.assignRoleToUser(userId, roleId);
           Map<String, String> response = new HashMap<>();
           response.put("message", "Role assigned successfully");
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @DeleteMapping("/{userId}/roles/{roleId}")
   public ResponseEntity<Map<String, String>> removeRole(
           @PathVariable Long userId,
           @PathVariable Long roleId) {
       try {
           userService.removeRoleFromUser(userId, roleId);
           Map<String, String> response = new HashMap<>();
           response.put("message", "Role removed successfully");
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
}