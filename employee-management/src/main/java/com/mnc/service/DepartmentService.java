package com.mnc.service;
import com.mnc.entity.Department;
import com.mnc.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DepartmentService {
   @Autowired
   private DepartmentRepository departmentRepository;
   @Autowired
   private AuditService auditService;
   public List<Department> getAllDepartments() {
       return departmentRepository.findAll();
   }
   public Page<Department> getAllDepartments(Pageable pageable) {
       return departmentRepository.findAll(pageable);
   }
   public Optional<Department> getDepartmentById(Long id) {
       return departmentRepository.findById(id);
   }
   public Optional<Department> getDepartmentByName(String name) {
       return departmentRepository.findByName(name);
   }
   public List<Department> getActiveDepartments() {
       return departmentRepository.findByActive(true);
   }
   public Page<Department> getActiveDepartments(Pageable pageable) {
       return departmentRepository.findByActive(true, pageable);
   }
   public Department createDepartment(Department department) {
       Department savedDepartment = departmentRepository.save(department);
       auditService.logAction("Department", savedDepartment.getId(), "CREATE", "System");
       return savedDepartment;
   }
   public Department updateDepartment(Long id, Department departmentDetails) {
       Department department = departmentRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
       department.setName(departmentDetails.getName());
       department.setDescription(departmentDetails.getDescription());
       department.setLocation(departmentDetails.getLocation());
       department.setActive(departmentDetails.getActive());
       Department updatedDepartment = departmentRepository.save(department);
       auditService.logAction("Department", updatedDepartment.getId(), "UPDATE", "System");
       return updatedDepartment;
   }
   public void deleteDepartment(Long id) {
       departmentRepository.deleteById(id);
       auditService.logAction("Department", id, "DELETE", "System");
   }
   public Boolean existsByName(String name) {
       return departmentRepository.existsByName(name);
   }
}