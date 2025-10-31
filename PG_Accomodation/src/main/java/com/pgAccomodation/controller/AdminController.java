package com.pgAccomodation.controller;

import com.pgAccomodation.dto.ApiResponse;
import com.pgAccomodation.entity.Admin;
import com.pgAccomodation.service.admin.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Admin Management APIs")
public class AdminController {
    
    private final AdminService adminService;
    
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    /**
     * Get admin by ID
     * GET /admin/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get admin by ID")
    public ResponseEntity<ApiResponse> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.findAdminById(id);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Admin retrieved successfully", 
            admin
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get all admins
     * GET /admin/all
     */
    @GetMapping("/all")
    @Operation(summary = "Get all admins")
    public ResponseEntity<ApiResponse> getAllAdmins() {
        ApiResponse response = new ApiResponse(
            true, 
            "Admins retrieved successfully", 
            adminService.getAllAdmins()
        );
        
        return ResponseEntity.ok(response);
    }
}