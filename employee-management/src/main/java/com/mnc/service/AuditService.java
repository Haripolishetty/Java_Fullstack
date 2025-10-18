package com.mnc.service;
import com.mnc.entity.Audit;
import com.mnc.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class AuditService {
   @Autowired
   private AuditRepository auditRepository;
   public List<Audit> getAllAudits() {
       return auditRepository.findAll();
   }
   public Optional<Audit> getAuditById(Long id) {
       return auditRepository.findById(id);
   }
   public List<Audit> getAuditsByEntity(String entityName, Long entityId) {
       return auditRepository.findByEntityNameAndEntityId(entityName, entityId);
   }
   public List<Audit> getAuditsByUser(String username) {
       return auditRepository.findByChangedBy(username);
   }
   public List<Audit> getAuditsByAction(String action) {
       return auditRepository.findByAction(action);
   }
   public Page<Audit> getAuditsByEntityName(String entityName, Pageable pageable) {
       return auditRepository.findByEntityName(entityName, pageable);
   }
   public List<Audit> getAuditsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
       return auditRepository.findByChangedAtBetween(startDate, endDate);
   }
   public Audit logAction(String entityName, Long entityId, String action, String changedBy) {
       Audit audit = new Audit();
       audit.setEntityName(entityName);
       audit.setEntityId(entityId);
       audit.setAction(action);
       audit.setChangedBy(changedBy);
       return auditRepository.save(audit);
   }
   public Audit logActionWithDetails(String entityName, Long entityId, String action,
                                     String changedBy, String oldValue, String newValue) {
       Audit audit = new Audit();
       audit.setEntityName(entityName);
       audit.setEntityId(entityId);
       audit.setAction(action);
       audit.setChangedBy(changedBy);
       audit.setOldValue(oldValue);
       audit.setNewValue(newValue);
       return auditRepository.save(audit);
   }
}