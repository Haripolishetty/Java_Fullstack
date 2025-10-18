package com.mnc.controller;
import com.mnc.entity.Employee;
import com.mnc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
   @Autowired
   private EmployeeService employeeService;
   @GetMapping
   public ResponseEntity<Map<String, Object>> getAllEmployees(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size,
           @RequestParam(defaultValue = "id") String sort,
           @RequestParam(defaultValue = "asc") String direction) {
       try {
           Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ?
                   Sort.Direction.DESC : Sort.Direction.ASC;
           Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
           Page<Employee> employeePage = employeeService.getAllEmployees(pageable);
           Map<String, Object> response = new HashMap<>();
           response.put("employees", employeePage.getContent());
           response.put("currentPage", employeePage.getNumber());
           response.put("totalItems", employeePage.getTotalElements());
           response.put("totalPages", employeePage.getTotalPages());
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @GetMapping("/{id}")
   public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
       return employeeService.getEmployeeById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/email/{email}")
   public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
       return employeeService.getEmployeeByEmail(email)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/department/{departmentId}")
   public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable Long departmentId) {
       return ResponseEntity.ok(employeeService.getEmployeesByDepartment(departmentId));
   }
   @GetMapping("/active")
   public ResponseEntity<List<Employee>> getActiveEmployees() {
       return ResponseEntity.ok(employeeService.getActiveEmployees());
   }
   @GetMapping("/search")
   public ResponseEntity<Map<String, Object>> searchEmployees(
           @RequestParam String keyword,
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size) {
       try {
           Pageable pageable = PageRequest.of(page, size);
           Page<Employee> employeePage = employeeService.searchEmployees(keyword, pageable);
           Map<String, Object> response = new HashMap<>();
           response.put("employees", employeePage.getContent());
           response.put("currentPage", employeePage.getNumber());
           response.put("totalItems", employeePage.getTotalElements());
           response.put("totalPages", employeePage.getTotalPages());
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @PostMapping
   public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
       try {
           if (employeeService.existsByEmail(employee.getEmail())) {
               return ResponseEntity.badRequest().build();
           }
           Employee createdEmployee = employeeService.createEmployee(employee);
           return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @PutMapping("/{id}")
   public ResponseEntity<Employee> updateEmployee(
           @PathVariable Long id,
           @RequestBody Employee employee) {
       try {
           Employee updatedEmployee = employeeService.updateEmployee(id, employee);
           return ResponseEntity.ok(updatedEmployee);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
       try {
           employeeService.deleteEmployee(id);
           Map<String, String> response = new HashMap<>();
           response.put("message", "Employee deleted successfully");
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @PatchMapping("/{id}/deactivate")
   public ResponseEntity<Map<String, String>> deactivateEmployee(@PathVariable Long id) {
       try {
           employeeService.deactivateEmployee(id);
           Map<String, String> response = new HashMap<>();
           response.put("message", "Employee deactivated successfully");
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
}