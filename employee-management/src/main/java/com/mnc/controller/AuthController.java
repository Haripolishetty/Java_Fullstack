package com.mnc.controller;
import com.mnc.entity.User;
import com.mnc.entity.Role;
import com.mnc.service.UserService;
import com.mnc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
   @Autowired
   private UserService userService;
   @Autowired
   private RoleService roleService;
   @PostMapping("/register")
   public ResponseEntity<?> registerUser(@RequestBody User user) {
       try {
           if (userService.existsByUsername(user.getUsername())) {
               return ResponseEntity.badRequest()
                       .body(createResponse(false, "Username already exists"));
           }
           if (userService.existsByEmail(user.getEmail())) {
               return ResponseEntity.badRequest()
                       .body(createResponse(false, "Email already exists"));
           }
           Role userRole = roleService.getRoleByName("ROLE_USER")
                   .orElseThrow(() -> new RuntimeException("Role not found"));
           user.getRoles().add(userRole);
           User createdUser = userService.createUser(user);
           return ResponseEntity.status(HttpStatus.CREATED)
                   .body(createResponse(true, "User registered successfully", createdUser.getId()));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(createResponse(false, "Error: " + e.getMessage()));
       }
   }
   @PostMapping("/login")
   public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
       try {
           String username = loginRequest.get("username");
           String password = loginRequest.get("password");
           User user = userService.getUserByUsername(username)
                   .orElseThrow(() -> new RuntimeException("User not found"));
           Map<String, Object> response = new HashMap<>();
           response.put("success", true);
           response.put("message", "Login successful");
           response.put("userId", user.getId());
           response.put("username", user.getUsername());
           response.put("email", user.getEmail());
           response.put("roles", user.getRoles());
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                   .body(createResponse(false, "Invalid credentials"));
       }
   }
   private Map<String, Object> createResponse(boolean success, String message) {
       Map<String, Object> response = new HashMap<>();
       response.put("success", success);
       response.put("message", message);
       return response;
   }
   private Map<String, Object> createResponse(boolean success, String message, Object data) {
       Map<String, Object> response = createResponse(success, message);
       response.put("data", data);
       return response;
   }
}