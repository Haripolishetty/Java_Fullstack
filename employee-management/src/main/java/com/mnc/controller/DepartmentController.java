package com.mnc.controller;
import com.mnc.entity.Department;
import com.mnc.service.DepartmentService;
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
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {
   @Autowired
   private DepartmentService departmentService;
   @GetMapping
   public ResponseEntity<Map<String, Object>> getAllDepartments(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size,
           @RequestParam(defaultValue = "id") String sort,
           @RequestParam(defaultValue = "asc") String direction) {
       try {
           Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ?
                   Sort.Direction.DESC : Sort.Direction.ASC;
           Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
           Page<Department> departmentPage = departmentService.getAllDepartments(pageable);
           Map<String, Object> response = new HashMap<>();
           response.put("departments", departmentPage.getContent());
           response.put("currentPage", departmentPage.getNumber());
           response.put("totalItems", departmentPage.getTotalElements());
           response.put("totalPages", departmentPage.getTotalPages());
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @GetMapping("/all")
   public ResponseEntity<List<Department>> getAllDepartmentsList() {
       return ResponseEntity.ok(departmentService.getAllDepartments());
   }
   @GetMapping("/{id}")
   public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
       return departmentService.getDepartmentById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/name/{name}")
   public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
       return departmentService.getDepartmentByName(name)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/active")
   public ResponseEntity<List<Department>> getActiveDepartments() {
       return ResponseEntity.ok(departmentService.getActiveDepartments());
   }
   @PostMapping
   public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
       try {
           if (departmentService.existsByName(department.getName())) {
               return ResponseEntity.badRequest().build();
           }
           Department createdDepartment = departmentService.createDepartment(department);
           return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @PutMapping("/{id}")
   public ResponseEntity<Department> updateDepartment(
           @PathVariable Long id,
           @RequestBody Department department) {
       try {
           Department updatedDepartment = departmentService.updateDepartment(id, department);
           return ResponseEntity.ok(updatedDepartment);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Map<String, String>> deleteDepartment(@PathVariable Long id) {
       try {
           departmentService.deleteDepartment(id);
           Map<String, String> response = new HashMap<>();
           response.put("message", "Department deleted successfully");
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
}