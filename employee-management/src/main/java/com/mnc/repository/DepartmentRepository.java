package com.mnc.repository;
import com.mnc.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
   Optional<Department> findByName(String name);
   List<Department> findByActive(Boolean active);
   Page<Department> findByActive(Boolean active, Pageable pageable);
   Boolean existsByName(String name);
}