package com.mnc.config;
import com.mnc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class DataInitializer implements CommandLineRunner {
   @Autowired
   private RoleService roleService;
   @Override
   public void run(String... args) throws Exception {
       roleService.initializeRoles();
       System.out.println("Default roles initialized: ROLE_ADMIN, ROLE_MANAGER, ROLE_USER");
   }
}