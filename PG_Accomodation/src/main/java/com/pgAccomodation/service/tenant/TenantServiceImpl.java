package com.pgAccomodation.service.tenant;

import com.pgAccomodation.dto.TenantRegistrationDTO;
import com.pgAccomodation.entity.Tenant;
import com.pgAccomodation.exception.BadRequestException;
import com.pgAccomodation.exception.DuplicateResourceException;
import com.pgAccomodation.exception.ResourceNotFoundException;
import com.pgAccomodation.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {
    
    private final TenantRepository tenantRepository;
    
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }
    
    @Override
    @Transactional
    public Tenant registerTenant(TenantRegistrationDTO dto) {
        // Validate age >= 18
        if (dto.getAge() < 18) {
            throw new BadRequestException("Tenant must be at least 18 years old to register");
        }
        
        // Check if email already exists
        if (tenantRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Tenant", "email", dto.getEmail());
        }
        
        // Check if phone already exists
        if (tenantRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicateResourceException("Tenant", "phone", dto.getPhone());
        }
        
        // Create tenant entity
        Tenant tenant = new Tenant();
        tenant.setFirstName(dto.getFirstName());
        tenant.setLastName(dto.getLastName());
        tenant.setEmail(dto.getEmail());
        tenant.setPhone(dto.getPhone());
        tenant.setPassword(dto.getPassword()); // In production, hash this!
        tenant.setAge(dto.getAge());
        tenant.setEmploymentPlace(dto.getEmploymentPlace());
        
        return tenantRepository.save(tenant);
    }
    
    @Override
    public Tenant findTenantById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant", "id", id));
    }
    
    @Override
    public Tenant findTenantByEmail(String email) {
        return tenantRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant", "email", email));
    }
    
    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }
}