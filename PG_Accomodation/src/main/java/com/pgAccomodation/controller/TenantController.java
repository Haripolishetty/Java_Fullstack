package com.pgAccomodation.controller;

import com.pgAccomodation.dto.ApiResponse;
import com.pgAccomodation.dto.TenantRegistrationDTO;
import com.pgAccomodation.entity.Tenant;
import com.pgAccomodation.service.tenant.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant")
@Tag(name = "Tenant", description = "Tenant Management APIs")
public class TenantController {
    
    private final TenantService tenantService;
    
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }
    
    /**
     * Register a new tenant
     * POST /tenant/register
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new tenant", 
               description = "Register a new tenant. Age must be at least 18 years.")
    public ResponseEntity<ApiResponse> registerTenant(@Valid @RequestBody TenantRegistrationDTO dto) {
        Tenant tenant = tenantService.registerTenant(dto);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Tenant registered successfully", 
            tenant
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * Get tenant by ID
     * GET /tenant/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get tenant by ID")
    public ResponseEntity<ApiResponse> getTenantById(@PathVariable Long id) {
        Tenant tenant = tenantService.findTenantById(id);
        
        ApiResponse response = new ApiResponse(
            true, 
            "Tenant retrieved successfully", 
            tenant
        );
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get all tenants
     * GET /tenant/all
     */
    @GetMapping("/all")
    @Operation(summary = "Get all tenants")
    public ResponseEntity<ApiResponse> getAllTenants() {
        ApiResponse response = new ApiResponse(
            true, 
            "Tenants retrieved successfully", 
            tenantService.getAllTenants()
        );
        
        return ResponseEntity.ok(response);
    }
}