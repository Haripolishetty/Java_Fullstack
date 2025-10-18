package com.mnc.repository;
import com.mnc.entity.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
   List<Audit> findByEntityNameAndEntityId(String entityName, Long entityId);
   List<Audit> findByChangedBy(String changedBy);
   List<Audit> findByAction(String action);
   Page<Audit> findByEntityName(String entityName, Pageable pageable);
   List<Audit> findByChangedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}