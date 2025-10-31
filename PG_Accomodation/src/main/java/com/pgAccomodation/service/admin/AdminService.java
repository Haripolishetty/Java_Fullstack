package com.pgAccomodation.service.admin;

import com.pgAccomodation.entity.Admin;
import java.util.List;

public interface AdminService {
    
    Admin findAdminById(Long id);
    
    Admin findAdminByUsername(String username);
    
    List<Admin> getAllAdmins();
}