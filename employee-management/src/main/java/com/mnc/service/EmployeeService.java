package com.mnc.service;
import com.mnc.entity.Employee;
import com.mnc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class EmployeeService {
   @Autowired
   private EmployeeRepository employeeRepository;
   @Autowired
   private AuditService auditService;
   public List<Employee> getAllEmployees() {
       return employeeRepository.findAll();
   }
   public Page<Employee> getAllEmployees(Pageable pageable) {
       return employeeRepository.findAll(pageable);
   }
   public Optional<Employee> getEmployeeById(Long id) {
       return employeeRepository.findById(id);
   }
   public Optional<Employee> getEmployeeByEmail(String email) {
       return employeeRepository.findByEmail(email);
   }
   public List<Employee> getEmployeesByDepartment(Long departmentId) {
       return employeeRepository.findByDepartmentId(departmentId);
   }
   public List<Employee> getActiveEmployees() {
       return employeeRepository.findByActive(true);
   }
   public Page<Employee> getActiveEmployees(Pageable pageable) {
       return employeeRepository.findByActive(true, pageable);
   }
   public Page<Employee> searchEmployees(String keyword, Pageable pageable) {
       return employeeRepository.searchEmployees(keyword, pageable);
   }
   public Employee createEmployee(Employee employee) {
       Employee savedEmployee = employeeRepository.save(employee);
       auditService.logAction("Employee", savedEmployee.getId(), "CREATE", "System");
       return savedEmployee;
   }
   public Employee updateEmployee(Long id, Employee employeeDetails) {
       Employee employee = employeeRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
       employee.setFirstName(employeeDetails.getFirstName());
       employee.setLastName(employeeDetails.getLastName());
       employee.setEmail(employeeDetails.getEmail());
       employee.setPhone(employeeDetails.getPhone());
       employee.setHireDate(employeeDetails.getHireDate());
       employee.setSalary(employeeDetails.getSalary());
       employee.setPosition(employeeDetails.getPosition());
       employee.setDepartment(employeeDetails.getDepartment());
       employee.setAddress(employeeDetails.getAddress());
       employee.setActive(employeeDetails.getActive());
       Employee updatedEmployee = employeeRepository.save(employee);
       auditService.logAction("Employee", updatedEmployee.getId(), "UPDATE", "System");
       return updatedEmployee;
   }
   public void deleteEmployee(Long id) {
       employeeRepository.deleteById(id);
       auditService.logAction("Employee", id, "DELETE", "System");
   }
   public void deactivateEmployee(Long id) {
       Employee employee = employeeRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
       employee.setActive(false);
       employeeRepository.save(employee);
       auditService.logAction("Employee", id, "DEACTIVATE", "System");
   }
   public Boolean existsByEmail(String email) {
       return employeeRepository.existsByEmail(email);
   }
}