package com.mnc.repository;
import com.mnc.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   Optional<Employee> findByEmail(String email);
   List<Employee> findByDepartmentId(Long departmentId);
   List<Employee> findByActive(Boolean active);
   Page<Employee> findByActive(Boolean active, Pageable pageable);
   @Query("SELECT e FROM Employee e WHERE " +
          "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
          "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
          "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
          "LOWER(e.position) LIKE LOWER(CONCAT('%', :keyword, '%'))")
   Page<Employee> searchEmployees(@Param("keyword") String keyword, Pageable pageable);
   Boolean existsByEmail(String email);
}