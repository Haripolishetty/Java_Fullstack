package com.mnc.entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
@Entity
@Table(name = "audits")
public class Audit {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "entity_name", nullable = false, length = 100)
   private String entityName;
   @Column(name = "entity_id", nullable = false)
   private Long entityId;
   @Column(nullable = false, length = 50)
   private String action;
   @Column(name = "changed_by", length = 100)
   private String changedBy;
   @CreationTimestamp
   @Column(name = "changed_at", updatable = false)
   private LocalDateTime changedAt;
   @Column(name = "old_value", columnDefinition = "TEXT")
   private String oldValue;
   @Column(name = "new_value", columnDefinition = "TEXT")
   private String newValue;
   @Column(columnDefinition = "TEXT")
   private String description;
   public Audit() {
   }
   public Audit(String entityName, Long entityId, String action, String changedBy) {
       this.entityName = entityName;
       this.entityId = entityId;
       this.action = action;
       this.changedBy = changedBy;
   }
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
this.id = id;
   }
   public String getEntityName() {
       return entityName;
   }
   public void setEntityName(String entityName) {
       this.entityName = entityName;
   }
   public Long getEntityId() {
       return entityId;
   }
   public void setEntityId(Long entityId) {
       this.entityId = entityId;
   }
   public String getAction() {
       return action;
   }
   public void setAction(String action) {
       this.action = action;
   }
   public String getChangedBy() {
       return changedBy;
   }
   public void setChangedBy(String changedBy) {
       this.changedBy = changedBy;
   }
   public LocalDateTime getChangedAt() {
       return changedAt;
   }
   public void setChangedAt(LocalDateTime changedAt) {
       this.changedAt = changedAt;
   }
   public String getOldValue() {
       return oldValue;
   }
   public void setOldValue(String oldValue) {
       this.oldValue = oldValue;
   }
   public String getNewValue() {
       return newValue;
   }
   public void setNewValue(String newValue) {
       this.newValue = newValue;
   }
   public String getDescription() {
       return description;
   }
   public void setDescription(String description) {
       this.description = description;
   }
}