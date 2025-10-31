package com.pgAccomodation.service.tenant;

import com.pgAccomodation.dto.TenantRegistrationDTO;
import com.pgAccomodation.entity.Tenant;

public interface TenantService {
    
    /**
     * Register a new tenant
     * Validates age >= 18 and unique email/phone
     */
    Tenant registerTenant(TenantRegistrationDTO dto);
    
    /**
     * Find tenant by ID
     */
    Tenant findTenantById(Long id);
    
    /**
     * Find tenant by email
     */
    Tenant findTenantByEmail(String email);
    
    /**
     * Get all tenants
     */
    java.util.List<Tenant> getAllTenants();
}